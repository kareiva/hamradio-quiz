#!/usr/bin/python
# Parse exported UBA HTML files into XML readable by the quiz app

import sys, getopt
from HTMLParser import HTMLParser

class MyHTMLParser(HTMLParser):
    def __init__(self):
        HTMLParser.__init__(self)
        self.lasttag = False
        self.qList = []
        self.directory = '/'
        self.indexcol = ';'
        self.Counter = 0

    def handle_starttag(self, tag, attrs):
        self.inLink = False
        if tag == 'img':
            for name,value in attrs:
                if name == 'src':
                    sys.stdout.write("images/" + value.strip())
        if tag == 'tr':
            sys.stdout.write("\n\n<question>\n")
        if tag == 'td':
            self.Counter += 1
            if self.Counter == 1:
                sys.stdout.write("\t<image>")
            elif self.Counter == 2:
                sys.stdout.write("\n\t<text>")
            elif self.Counter == 7:
                sys.stdout.write("<answer>")
            elif self.Counter < 7:
                sys.stdout.write("<choice>")

    def handle_endtag(self, tag):
        if tag == 'tr':
            sys.stdout.write("\n</question>")
            self.Counter = 0
        if tag == 'img':
            sys.stdout.write("</image>")
        if tag == 'td':
            if self.Counter == 1:
                sys.stdout.write("</image>")
            elif self.Counter == 2:
                sys.stdout.write("</text>")
            elif self.Counter == 7:
                sys.stdout.write("</answer>")
            elif self.Counter < 7:
                sys.stdout.write("</choice>")


    def handle_data(self, data):
        if self.lasttag == 'td' and self.Counter == 7:
            if data == "A":
                sys.stdout.write("1")
            elif data == "B":
                sys.stdout.write("2")
            elif data == "C":
                sys.stdout.write("3")
            elif data == "D":
                sys.stdout.write("4")
        elif self.lasttag == 'td':
            sys.stdout.write(data)

def main(argv):
    inputfile = ''
    outputfile = ''
    try:
        opts, args = getopt.getopt(argv,"hi:o:",["ifile=","ofile="])
    except getopt.GetoptError:
        print 'test.py -i <inputfile> -o <outputfile>'
        sys.exit(2)
    for opt, arg in opts:
        if opt == '-h':
            print 'test.py -i <inputfile> -o <outputfile>'
            sys.exit()
        elif opt in ("-i", "--ifile"):
            inputfile = arg
        elif opt in ("-o", "--ofile"):
            outputfile = arg

    # sys.stdout.write('<?xml version="1.0" encoding="UTF-8"?><quiz>')
    sys.stdout.write('\n\t<category>\n\t<title>'+inputfile+'</title>')
    parser = MyHTMLParser()
    parser.feed(open(inputfile).read())
    sys.stdout.write('\t\n<category>')
    # sys.stdout.write('\n<quiz>')

if __name__ == "__main__":
    main(sys.argv[1:])

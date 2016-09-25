import xml.sax
import os.path
from string import Template
from os import makedirs
from os.path import expandvars, normpath

RESOURCEDIR = './resources'
XMLDIR = os.path.join(RESOURCEDIR, 'xmlfiles')
OUTDIR = './target/htmlfiles'
TEMPATEPATH = os.path.join(RESOURCEDIR, 'post-template.html')


class BodyHandler(xml.sax.ContentHandler):
    def __init__(self):
        self.rowcount = 0
        makedirs(OUTDIR, exist_ok=True)
        self.html_template = '<html>${Body}</html>'
        with open(TEMPATEPATH, 'r') as file:
            self.html_template = Template(file.read())

    def startElement(self, name, attrs):
        if name != 'row': return
        self.rowcount += 1
        body = attrs.get('Body', None)
        outfile = os.path.join(OUTDIR, 'post-%s.html' % self.rowcount)

        dct = {'Title': attrs.get('Title', 'No title')}
        dct['Body'] = attrs.get('Body', '<em>no body text</em>') * 100
        self.export_file(outfile, dct)

    def export_file(self, path, context={}):
        path = normpath(expandvars(path))
        html = self.html_template.safe_substitute(context)
        # print("exporting to:%s\t bytes:%s" % (path, len(html)))
        with open(path, 'w', encoding='utf-8') as file:
            file.write(html)


parser = xml.sax.make_parser()
handler = BodyHandler()
parser.setContentHandler(handler)
with open(os.path.join(XMLDIR, 'Posts.xml'), 'r', encoding='utf-8') as file:
    parser.parse(file)
print("parsed %s rows " % handler.rowcount)

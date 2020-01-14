import requests_html
import json

novel_url = "https://www.qidian.com/"

session = requests_html.HTMLSession()

response = session.get(novel_url)
target_lis = response.html.find(".center-book-list ul li") # 一共得到12个

novel_list=[]

with open("novel_list.txt","w") as novel_file:
    for li in target_lis:
        img = li.xpath('//div[@class="book-img"]/a/img/@src')[0]
        title = li.xpath('//div[@class="book-info"]/h3/a/text()')[0]
        abstract = li.xpath('//div[@class="book-info"]/p/text()')[0]
        author = li.xpath('//div[@class="book-info"]//a[@class="author"]/text()')[0]
        novel = {
            "img":img,
            "title":title,
            "abstract":abstract,
            "author":author
        }
        novel_list.append(novel)
    novel_file.write(json.dumps(novel_list, ensure_ascii=False))


print("done!")
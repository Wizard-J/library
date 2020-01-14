# http测试：http://httpbin.org/
import requests
import json

session = requests.Session()

def get_class_list():
    create_class_url = "http://localhost:8080/library/api/get/classificationlist"
    class_list = session.get(create_class_url)
    return class_list.json()
    

def create_class():
    class_list = get_class_list()
    create_class_url = "http://localhost:8080/library/api/createclassfication"

    for _class in class_list:
        params = {
            "index": _class["id"],
            "className": _class["className"]
        }
        response = session.post(create_class_url,params=params)
        print(response.json())


def return_book():
    get_book_list_url = "http://localhost:8080/library/api/get/booklist"
    return_book_url = "http://localhost:8080/library/api/returnbook"
    
    book_list = (session.get(get_book_list_url)).json()
    for book in book_list:
        params = {
            "bookId":book["id"]
        }
        print((session.get(return_book_url, params=params)).json())


def borrow_book():
    get_book_list_url = "http://localhost:8080/library/api/get/booklist"
    borrow_book_url = "http://localhost:8080/library/api/borrowbook"
    book_list = (session.get(get_book_list_url)).json()
    # print(book_list)
    for book in book_list:
        params = {
            "bookId":book["id"]
        }
        print((session.get(borrow_book_url, params=params)).json())


def write_novel():
    
    with open("novel_list.txt","r") as novles:
        novel_list = json.loads(novles.read())
    
    create_book_url = "http://localhost:8080/library/api/create/book"
    for item in novel_list:
        params = {}
        params["name"] = item["title"]
        params["author"] = item["author"]
        params["publish"] = "起点中文网"
        params["isbn"] = "-"
        params["introduction"] = item["abstract"]
        params["language"] = "中文"
        params["price"] = 0
        params["pubdate"] = "1970-01-01"
        params["classId"] = "1"
        params["pressmark"] = "1"
        params["imgSrc"] = item["img"]
        response = session.post(create_book_url, params=params)
        print(response.json())
        print("done!")




def write_user():
    signin_url = "http://localhost:8080/library/api/signin"
    user = {
        "account":"admin",
        "passwd":"123",
        "name":"小盐小盐小盐",
        "sex":"0",
        "birthday":"1970-01-01",
        "address":"武汉孝感",
        "number":"12345678",
        "isAdmin":"1"
    }

    user_list = [
        {
            "account":"wizardj",
            "passwd":"321",
            "name":"小盐的bf",
            "sex":"1",
            "birthday":"1993-01-01",
            "address":"武汉啦",
            "number":"123456789",
            "isAdmin":"0"
        },{
            "account":"dragonborn",
            "passwd":"123456",
            "name":"龙傲天",
            "sex":"0",
            "birthday":"2000-12-01",
            "address":"上海",
            "number":"87654321",
        },{
            "account":"reader1",
            "passwd":"123456",
            "name":"读者1",
            "sex":"0",
            "birthday":"2010-12-01",
            "address":"北京",
            "number":"87654321",
        },{
            "account":"reader2",
            "passwd":"123456",
            "name":"读者2",
            "sex":"0",
            "birthday":"2020-12-01",
            "address":"深圳",
            "number":"87654321",
        },{
            "account":"reader3",
            "passwd":"123456",
            "name":"读者3",
            "sex":"0",
            "birthday":"2030-12-01",
            "address":"杭州",
            "number":"87654321",
        }
    ]

    for reader in user_list:
        response = session.post(signin_url, params=reader)
        print(response.json())
    
def log_in():
    login_url = "http://localhost:8080/library/api/login"

    user_info = {
        "account":"admin",
        "passwd":"123"
    }
    
    response = session.post(login_url, params=user_info)
    print(response.json())


log_in()
create_class()
# get_class_list()
# return_book()
# borrow_book()
# write_novel()
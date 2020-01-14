# 安装

```shell
$ git clone git@github.com:Wizard-J/library.git

$ cd Books-Management-System

$ mvn clean compile

$ mvn clean package

$ mvn clean install
```

# 404

| 方法名   | 请求方式 | 接口               | 参数 | 返回值                                                       |
| -------- | :------- | ------------------ | ---- | ------------------------------------------------------------ |
| 非法请求 | *        | /api下任意非法请求 | *    | {<br/>    "msg": "年轻人，你的接口可能写错了哟~",<br/>    "stateCode": "0"<br/>} |



# user

| 方法名       | 请求方式 | 接口                  | 参数                                                         | 返回值                                                       |
| :----------- | -------- | --------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 注册         | POST     | /api/signin           | account(账号)，<br />passwd(密码),<br />name(姓名),<br />sex(性别),<br />birthday(生日),<br />address(住址),<br />number(电话号码),<br />is_admin(管理员身份) |                                                              |
| 登陆         | POST     | /api/login            | id,<br />passwd                                              | {<br/>    "msg": "请检查参数newPasswd或oldPasswd是否有误！"\\"账号或密码错误！"\\"管理员登陆成功！" \\"读者登陆成功！",<br/>    "stateCode": "0"\\"1"\\"2"<br/>} |
| 登出         | GET      | /api/logout           | None                                                         | {<br/>    "msg": "已登出！",<br/>    "stateCode": "1"<br/>}  |
| 修改密码     | POST     | /api/update/passwd    | oldPasswd,<br /> newPasswd                                   | {<br/>    "msg": "密码修改成功！"\\\"密码修改失败！"\\"旧密码错误！"\\"不行，你还没有登陆哟！"\\"请检查参数newPasswd或oldPasswd是否有误！",<br/>    "stateCode": "1"\\"0"<br/>} |
| 获取用户列表 | GET      | /api/get/userlist     | *                                                            | {<br/>    "msg": "获取成功！"\\"你不是管理员！",<br/>    "data": [],<br />    "stateCode": "1"\\"0"<br/>} |
| 查询用户信息 | GET      | /api/get/userInfo     | id                                                           | {<br/>    "msg": "查询到信息！"\\"你没有登录哟~"\\"不行哟，你不是管理员。"\\"此id不存在！",<br/>    "data": {<br/>        "id": "1501014101",<br/>    },<br/>    "stateCode": "1"\\"0"<br/>} |
| 查询个人信息 | GET      | /api/get/personalinfo | *                                                            | {<br/>    "msg": "查询到信息！"\\""你没有登录哟~"",<br/>    "data": {<br/>        "id": "123456",<br/>        },<br/>    "stateCode": "1"\\"0"<br/>} |
| 删除用户     | GET      | /api/delete/user      | id                                                           | {<br/>    "msg": "删除成功！"\\"你不是管理员！"\\"删除失败！用户信息不存在",<br/>    "stateCode": "1"\\"0"<br/>} |



# BOOK

| 方法名       | 请求方式 | 接口              | 可选参数                                                     | 返回值                                                       |
| :----------- | -------- | ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 获取书籍列表 | GET      | /api/get/booklist | *                                                            | [<br/>    {<br/>        "bookId": 10000001,<br/>        "name": "大雪中的山庄",<br/>        "author": "东野圭吾 ",<br/>        "publish": "北京十月文艺出版社",<br/>        "isbn": "9787530216835",<br/>        "introduction": "东野...",<br/>        "language": "中文",<br/>        "price": 35,<br/>        "pubdate": "2017-06-01",<br/>        "classId": 9,<br/>        "pressmark": 13,<br/>        "state": 1<br/>    }] |
| 删除图书     | GET      | /api/delete/book  | bookId                                                       | {<br/>    "msg": "传入参数bookId有误"\\"图书删除失败！"\\"图书删除成功！"\\"你还没有登陆或者你不是管理员！",<br/>    "stateCode":"0"\\"1"<br/>} |
| 新增图书     | POST     | /api/create/book  | 可选参数：<br />name(书名，必填),<br />author(作者),<br />publish(出版社),<br />isbn(ISBN),<br />introduction(简介),<br />language(语言),<br />price(价格),<br />pubdate(出版日期1999-01-02),<br />classId(书籍所属类别，必填),<br />pressmark(所在书架号) | {<br/>    "msg": "图书添加成功！"\\\"图书添加失败！"\\"至少要填写书籍名称和类别编号（1-22，22代表综合类）"\\"你还没有登陆或者你不是管理员！",<br/>    "stateCode": "1"\\"0"<br/>} |
| 更新图书     | POST     | /api/update/book  | 可选参数：<br />name(书名),<br />author(作者),<br />publish(出版社),<br />isbn(ISBN),<br />introduction(简介),<br />language(语言),<br />price(价格),<br />pubdate(出版日期1999-01-02),<br />classId(书籍所属类别),<br />pressmark(所在书架号) | {<br/>    "msg": "图书修改成功！"\\"图书修改失败！"\\"还没有指定bookId！"\\"你还没有登陆或者你不是管理员！",<br/>    "stateCode": "1"\\"0"<br/>} |
| 查找图书     | GET      | /api/query/book   | 二选一参数：<br />bookId(可选),<br />keywords(可选)          | {<br/>    "msg": "查找成功！",<br/>    "result": {<br/>        "bookId": 50..,<br/>        "name": "....",<br/>        "author": "... ",<br/>        "publish": "....",<br/>        "isbn": "978..",<br/>        "introduction": "...",<br/>        "language": "中文",<br/>        "price": 36,<br/>        "pubdate": "2..",<br/>        "classId": 9,<br/>        "pressmark": 13,<br/>        "state": 1<br/>    },<br/>    "stateCode": "1"<br/>} |

# Loan

| 方法名         | 请求方式 | 接口                    | 可选参数     | 返回值                                                       |
| :------------- | -------- | ----------------------- | ------------ | ------------------------------------------------------------ |
| 借书           | GET      | /api/borrowbook         | bookId(必填) | {<br/>    "msg": "你没有登录哟！"\\"Done！"\\"系统繁忙，请稍后重试！",<br/>    "stateCode":"0"\\"1"<br/>} |
| 还书           | GET      | /api/returnbook         | bookId       | {<br/>    "msg": "Done！"\\"系统繁忙，请稍后重试！",<br/>    "stateCode":"0"\\"1"<br/>} |
| 管理员查看日志 | POST     | /api/query/logs         | *            | {<br/>    "msg": "一大波图书日志来了~！"\\\"你没有登录哟~或者说你不是管理员哟~",<br/>    "stateCode": "1"\\"0"<br/>} |
| 查看个人日志   | POST     | /api/query/personallogs | *            | {<br/>    "msg": "一大波图书日志来了~！"\\"你没有登录哟~",<br/>    "data": [<br/>        {    ...    }<br/>    ],<br/>    "stateCode": "1"<br/>} |

# Classification



| 方法名               | 请求方式 | 接口                              | 可选参数                       | 返回值                                                       |
| :------------------- | -------- | --------------------------------- | ------------------------------ | ------------------------------------------------------------ |
| 按照分类获取图书列表 | GET      | /api/get/booklistbyclassification | classId                        | [<br />  {<br />  }<br />]                                   |
| 获取全部分类列表     | GET      | /api/get/classificationlist       | *                              | [<br />  {<br />  }<br />]                                   |
| 更新分类条目         | POST     | /api/updateclassificationlist     | id,<br />index,<br />className | {<br/>    "msg": "更新成功！"\\\"你还没有登陆或者你不是管理员！"\\"系统繁忙，请稍后重试！",<br/>    "stateCode": "1"\\"0"<br/>} |
| 删除分类条目         | GET      | /api/deleteclassfication          | id                             | {<br/>    "msg": "删除成功！"\\"系统繁忙，请稍后重试！"\\"你还没有登陆或者你不是管理员！",<br/>      "stateCode": "1"\\"0"<br/>} |
| 新增分类             | POST     | /api/createclassfication          | id,<br />index,<br />className | {<br/>    "msg": "添加成功！"\\"系统繁忙，请稍后重试！"\\"你还没有登陆或者你不是管理员！",<br/>      "stateCode": "1"\\"0"<br/>} |

# 


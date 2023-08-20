### 后续功能

#### 博客功能优化2.x版本

作者必须要绑定对应的`User ID`
支持文章中插入图片
作者这里就不需要填写了默认`userName`
	
#### 大的功能点

邀请注册功能
`VIP`
`shop`和`blog`的`UI`优化
评论功能
`firebase`推送

#### 以下是我在做的时候的一些思路和想法，简单记录

评论表字段设计
id
user_id
Article_id
nickname
Avatar
content
like
Level:为一级评论时parent_comment_id为null，当为二级评论时parent_comment_id  为一级评论ID
parent_comment_id:回复的父级ID default null
create_timer
update_timer

推送firebase_client_token 表
我评论了你，我后端的根据你的id找到对应的client_tokens,
而在这之前每个用户打开我的网站的时候，都需要去请求notification获得对应的client_token然后保存数据库
firebase
Id
user_id
client_token
subscription 保留字段，之后3.x版本的时候我会将文章进行分类。在根据订阅进行推送。因为firebase支持的主题推送一个应用智能有2000个，所以如果根据用户订阅的博主进行推送不可行
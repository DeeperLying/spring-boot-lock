### 关于停更的说明
我之前一直以学习前后端开发的路线来要求自己，做一个全栈开发工程师。所以我会去学习后端的开发语言，这也是
我开这个项目的原因，陆陆续续做了一些功能带着学习的目标的去做的相对来说没有那么快，不过这个阶段我也却是学到了
很多东西，开阔了视野不会像之前只站在前端的角度看待问题。但是我最近出去走了走看了一看，很多场景让我意识到不管是
最求技术还是想要看的更高看的更远，还是想要了解除了大陆以外的另外的世界，都让我迫切的意识到英语的重要性我必须要学好英语。
关于学习我给我定了几个目标，在我没有完成之前我应该会更新的比较少了。我希望在明年9月份左右可以全部实现。加油 加油。

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
mysql版本迭代管理。我想在每增加一个字段我都需要写一个mysql的允许环境，实在是
不好维护而且这玩意其它数据库也不支持。

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
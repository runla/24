package com.yinghai.a24divine_user.bean;

import java.util.List;

/**
 * Created by：fanson
 * Created on：2017/10/17 11:36
 * Describe：评论的Bean
 */

public class CommentBean {

    /**
     * code : 1
     * msg : success
     * data : {"comments":[{"commentId":13,"ctArticleId":1,"ctContent":"测试","ctCreateTime":"2017-11-02 15:53:52","ctDiscussantId":3,"ctInvisible":false,"ctProductId":0,"ctType":1,"img":"","nick":"曾","userId":3}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<CommentsBean> comments;

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class CommentsBean {
            /**
             * commentId : 13
             * ctArticleId : 1
             * ctContent : 测试
             * ctCreateTime : 2017-11-02 15:53:52
             * ctDiscussantId : 3
             * ctInvisible : false
             * ctProductId : 0
             * ctType : 1
             * img :
             * nick : 曾
             * userId : 3
             */

            private int commentId;
            private int ctArticleId;
            private String ctContent;
            private String ctCreateTime;
            private int ctDiscussantId;
            private boolean ctInvisible;
            private int ctProductId;
            private int ctType;
            private String img;
            private String nick;
            private int userId;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public int getCtArticleId() {
                return ctArticleId;
            }

            public void setCtArticleId(int ctArticleId) {
                this.ctArticleId = ctArticleId;
            }

            public String getCtContent() {
                return ctContent;
            }

            public void setCtContent(String ctContent) {
                this.ctContent = ctContent;
            }

            public String getCtCreateTime() {
                return ctCreateTime;
            }

            public void setCtCreateTime(String ctCreateTime) {
                this.ctCreateTime = ctCreateTime;
            }

            public int getCtDiscussantId() {
                return ctDiscussantId;
            }

            public void setCtDiscussantId(int ctDiscussantId) {
                this.ctDiscussantId = ctDiscussantId;
            }

            public boolean isCtInvisible() {
                return ctInvisible;
            }

            public void setCtInvisible(boolean ctInvisible) {
                this.ctInvisible = ctInvisible;
            }

            public int getCtProductId() {
                return ctProductId;
            }

            public void setCtProductId(int ctProductId) {
                this.ctProductId = ctProductId;
            }

            public int getCtType() {
                return ctType;
            }

            public void setCtType(int ctType) {
                this.ctType = ctType;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj == null) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                if (obj instanceof CommentsBean) {
                    CommentsBean bean = (CommentsBean) obj;
                    if (this.commentId == bean.getCommentId()) {
                        return true;
                    } else {
                        return false;
                    }

                }
                return false;
            }
        }
    }
}

package com.coin.enums;

public enum LikeAndFavTypeEnum {

    ARTICLE("App\\Models\\Article", "文章"),
    THREAD("App\\Models\\Thread", "帖子"),
    THREAD_REPLY("App\\Models\\ThreadReply", "帖子回复"),
    GUESS("App\\Models\\Guess", "竞猜");

    private String type;
    private String typeName;

    LikeAndFavTypeEnum(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

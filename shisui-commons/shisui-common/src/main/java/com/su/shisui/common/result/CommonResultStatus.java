package com.su.shisui.common.result;

/**
 * 返回code
 * @author all
 */
public enum CommonResultStatus implements ResultStatus{

    SUCCESS(0, "成功"),
    ERROR(-1,"失败");
//    OUT_LOGIN_STATUS(18001,"用户没有登录"),
//    INVALIDATION_LOGIN_STATUS(18002,"登录状态失效,SESSION过期"),
//    FAILED_SESSION_STATUS(18003,"SESSION,oppo验证失败"),
//    LOGIN_FAILED_OUT(18004,"用户退出登录，通过sessionID获取用户数据为空"),
//    DUBBO_TIME_OUT(18005,"dubbo服务连接超时"),
//    JUMP_LOGIN(18006,"跳转首页"),
//
//    REFUSE_LOGIN(18010,"不是论团请求，拒绝登陆"),
//
//    LOCKING_USER(18007,"用户被锁定，禁止登陆"),
//    PROHIBIT_VISIT_USER(18008,"用户被禁止访问"),
//    NO_PERMISSION_URL(18010,"没有权限"),
//    REFERER(18009,"不合法的请求链接"),
//
//    INVALID_ID(19001,"ID不能为空"),
//    INVALID_LIST(19002,"数据不能为空"),
//    INVALID_CREDITSHIGHER_SMALLER_THAN_LOWER(19003,"成长值上限不能低于成长值下限"),
//    INVALID_CREDITSHIGHER_UNCHANGE(19004,"最高等级的成长值上限不能修改"),
//    INVALID_CREDITSHIGHER_BIGGER_THAN_HIGHER(19005,"成长值上限不能大于下一等级成长值上限"),
//    INVALID_LEVEL_NAME_EXIST(19006,"用户等级名称已存在"),
//    INVALID_GROUP_NOT_EXIST(19007,"该用户没有普通群组"),
//    INVALID_GROUPSYS_NOT_ONLY(19008,"用户只能存在一个职能群组"),
//    INVALID_ENDTIME_SMALLER_THAN_STARTTIME(19009, "结束时间不能低于起始时间"),
//    INVALID_ENDTIME_UNCHANGE(19010, "最高等级的结束时间不能修改"),
//    INVALID_ENDTIME_BIGGER_THAN_HIGHER(19011, "成长值上限不能大于下一等级成长值上限"),
//    INVALID_NOT_HAVE_POWER(19012, "当前用户没有权限"),
//    FAILED_FOLLOW(19013,"关注失败"),
//    FAILED_FOLLOWED(19014,"已经关注"),
//    CANCEL_FAILED_FOLLOWED(19015,"没有关注用户,不能取消关注"),
//    LITTLEBLACK_HOUSE(19016,"用户被关入小黑屋"),
//    ADD_PRODUCT_FAIL(20001,"已经有此code的产品，不能重复添加"),
//
//
//    MESSAGE_BLACK_LIST_YOUR(19017,"你加对方黑名单"),
//    MESSAGE_SHIELD_OTHER(19018,"你屏蔽对方"),
//    MESSAGE_USER_OPERA(19019,"用户状态不正常"),
//    MESSAGE_POST_OPERA(19020,"此帖已经删除,不可操作"),
//    NOT_SEND_SKITTLE(19021,"你的彩虹糖不足了"),
//    NOT_SEND_NUM(19022,"今日发糖次数已达上限"),
//    NOT_GET_NUM(19023,"收糖次数已经到达上限"),
//    INVALID_FID_TID(19024,"数据版块不能相同"),
//    MESSAGE_BLACK_LIST_OTHER(19025,"对方加你黑名单"),
//    PROHIBIT_SPEECH(20280, "用户已被禁言"),
//    INSERT_FAIL_DUPLICATE_KEY(20001,"已经有权限数据，不能新增，请直接修改原有权限数据或者删除后再添加"),
//    CREATE_NUM_UPPER_LIMIT(19026,"发布数量达到上限"),
//    FILE_URL_ERROR(19027,"文件域名不合法"),
//    IS_ROOM_ERROR(19028,"帖子在小黑屋不能进行此操作"),
//    DELETE_FAILD(10086,"不能删除，已经添加关联数据"),
//    PREFIX_FAIL(10087,"请上传正常格式"),
//    NOT_SEND_REASON(10088,"请选择发糖理由"),
//    MESSAGE_COMMENT_NOT(10089,"此评论已经删除,不可操作"),
//    SKITTLE_NOT(10090,"不能给自己发糖"),
//    PATAM_NOT_COVERSDATA(10091,"图片帖封面不能为空"),
//    PATAM_NOT_CONTENTDATA(10092,"图片帖图片不能为空"),
//    PLATFORM_MESSAGE_DATA_FAIL(10093,"临时消息数据错误"),
//    MAX_FILE_SIZE_FAIL(10094,"文件大小不能超过5MB"),
//    MAX_IMAGE_FILE_SIZE_FAIL(10094,"文件大小不能超过10MB"),
//
//    NOBOADAUTH(10095,"该用户没有此版块权限"),
//    LENTHTOOLONG(10096,"输入的用户id不正确"),
//    ADDLIKE_FAIL(10097,"该用户已经点过赞，不能重复点赞"),
//    LENTHTOLONG(10098,"描述过长"),
//    NOT_NULL(10099,"数据不能为空"),
//
//    TEXT_TOO_LONG(10100,"文本过长"),
//    ID_NOT_TRUE(10101,"帖子不存在"),
//    BANNER_FIVE_SIZE(10102,"最多允许关联5个话题"),
//    IMAGE_PREFIX_FAIL(10103,"请上传正确的图片格式,如:PNG JPEG JPG"),
//    LENTHTOOLONG_POSTSID(10104,"帖子id不正确"),
//    OUT_ADD(10105,"已存在该风格版块,新增失败"),
//    OUT_DEL(10106,"该版块为有效状态,删除失败"),
//    CANCEL_LIKE_FAIL(10107,"该用户未点赞，不能取消点赞"),
//    USER_LOGIN_REFRESH(10110,"刷新用户权限");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;


    CommonResultStatus(int code, String message) {
        this.setCode(code);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }
}

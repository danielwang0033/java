package com.coin.service.impl;

import com.coin.entity.TDictI18n;
import com.coin.entity.TDictI18nExample;
import com.coin.i18n.I18nUtil;
import com.coin.mapper.TDictI18nMapper;
import com.coin.req.DictReq;
import com.coin.service.DictI18nService;
import com.coin.service.util.LocalCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DictI18nServiceImpl implements DictI18nService {

    private static final Logger logger = LoggerFactory.getLogger(DictI18nServiceImpl.class);

    @Resource
    private TDictI18nMapper tDictI18nMapper;

    @Override
    public List<TDictI18n> findByLanguage(String language) {
        TDictI18nExample example = new TDictI18nExample();
        TDictI18nExample.Criteria criteria = example.createCriteria();
        criteria.andLanguageEqualTo(language);
        criteria.andStatusEqualTo(1);
        criteria.andTranslationIsNotNull();
        criteria.andTranslationNotEqualTo("");
        return tDictI18nMapper.selectByExample(example);
    }

    @Override
    public void refresh() {
        LocalCache.deleteCache(LocalCache.I18N_CACHE);
        if (I18nUtil.ENABLE && !"zh".equals(I18nUtil.LANGUAGE)) {
            logger.info("开始加载国际化资源");
            Map<String, String> map = new HashMap<>();
            List<TDictI18n> list = this.findByLanguage(I18nUtil.LANGUAGE);
            for (TDictI18n item : list) {
                map.put(item.getZhCode(), item.getTranslation());
            }
            I18nUtil.initCache(map);
            logger.info("已加载{}条资源", map.size());
        } else {
            logger.info("未开启国际化 {},{}", I18nUtil.ENABLE, I18nUtil.LANGUAGE);
        }
    }

    @Override
    public void addBatch(DictReq req) {
        // ctl-1
        /*Map<String, String> ctlMap = new HashMap<>();
        ctlMap.put("保存成功", "đã lưu thành công");
        ctlMap.put("操作成功", "Hoạt động thành công");
        ctlMap.put("操作失败", "lỗi hệ thống");
        ctlMap.put("查询失败", "Truy vấn không thành công");
        ctlMap.put("抽奖失败", "Xổ số thất bại");
        ctlMap.put("登录成功", "đăng nhập thành công");
        ctlMap.put("登录失败", "Đăng nhập thất bại");
        ctlMap.put("兑换失败", "Đổi thưởng không thành công");
        ctlMap.put("发送成功", "Gửi thành công");
        ctlMap.put("发送失败", "Gửi thất bại");
        ctlMap.put("发帖成功", "Đăng thành công");
        ctlMap.put("发帖失败", "Đăng bài không thành công");
        ctlMap.put("更新成功", "hoàn thành cập nhật");
        ctlMap.put("更新失败", "Cập nhật không thành công");
        ctlMap.put("关注成功", "Đã theo dõi");
        ctlMap.put("关注失败", "Tập trung vào thất bại");
        ctlMap.put("回复成功", "Trả lời thành công");
        ctlMap.put("回复失败", "Trả lời không thành công");
        ctlMap.put("回帖成功", "Trả lời thành công");
        ctlMap.put("回帖失败", "Trả lời không thành công");
        ctlMap.put("结算失败", "Giải quyết không thành công");
        ctlMap.put("结算完成", "Quyết toán hoàn tất");
        ctlMap.put("金额必须为正数", "Số tiền phải dương");
        ctlMap.put("两次输入的密码不一致", "Mật khẩu nhập hai lần không nhất quán");
        ctlMap.put("两次输入密码不同", "Mật khẩu khác nhau được nhập hai lần");
        ctlMap.put("密码输入错误", "mật khẩu không đúng");
        ctlMap.put("评论成功", "Bình luận thành công");
        ctlMap.put("评论失败", "Bình luận không thành công");
        ctlMap.put("签到失败", "Đăng nhập không thành công");
        ctlMap.put("抢购成功", "Mua hàng thành công");
        ctlMap.put("请求失败", "Yêu cầu không thành công");
        ctlMap.put("请先登录", "vui lòng đăng nhập trước");
        ctlMap.put("请至少添加一个投注项", "Vui lòng thêm ít nhất một cược");
        ctlMap.put("取消关注成功", "Hủy theo dõi thành công");
        ctlMap.put("取消关注失败", "Hủy theo dõi không thành công");
        ctlMap.put("删除成功", "đã xóa thành công");
        ctlMap.put("删除失败", "không thể xóa");
        ctlMap.put("上传成功", "Tải lên thành công");
        ctlMap.put("上传视频成功", "Video đã được tải lên thành công");
        ctlMap.put("上传图片成功", "Hình ảnh được tải lên thành công");
        ctlMap.put("审批成功", "Phê duyệt thành công");
        ctlMap.put("审批失败", "Phê duyệt không thành công");
        ctlMap.put("添加成功", "Thêm thành công");
        ctlMap.put("添加失败", "thêm thất bại");
        ctlMap.put("投诉成功", "Khiếu nại thành công");
        ctlMap.put("投注成功", "đặt cược thành công");
        ctlMap.put("投注失败", "Đặt cược không thành công");
        ctlMap.put("退出成功", "thoát thành công");
        ctlMap.put("退出失败", "Thoát không thành công");
        ctlMap.put("未通过邮件验证用户将无法使用找回密码功能", "Người dùng chưa được xác minh qua email sẽ không thể sử dụng chức năng lấy lại mật khẩu.");
        ctlMap.put("系统将要发送一封验证邮件到您的邮箱", "Hệ thống sẽ gửi email xác minh tới hộp thư của bạn");
        ctlMap.put("修改成功", "Đã sửa đổi thành công");
        ctlMap.put("修改密码成功", "mật khẩu đã được cập nhật");
        ctlMap.put("修改密码失败", "Không thể thay đổi mật khẩu");
        ctlMap.put("修改失败", "không chỉnh sửa được");
        ctlMap.put("验证成功", "Xác minh thành công");
        ctlMap.put("验证失败", "xác minh không hoàn thành");
        ctlMap.put("用户不存在", "người dùng không tồn tại");
        ctlMap.put("用户名已被占用", "Tên người dùng đã được sử dụng");
        ctlMap.put("邮件即将送达", "Thư sẽ được gửi sớm");
        ctlMap.put("邮箱已被占用", "Email đã bị chiếm dụng");
        ctlMap.put("中奖金额计算撤销失败", "Tính toán số tiền thắng và thu hồi không thành công");
        ctlMap.put("中奖金额计算撤销完成", "Tính toán số tiền thắng và hủy hoàn tất");
        ctlMap.put("中奖金额计算失败", "Tính toán số tiền thắng không thành công");
        ctlMap.put("中奖金额计算完成", "Việc tính toán số tiền thắng đã hoàn tất");
        ctlMap.put("重置密码成功", "Đặt lại mật khẩu thành công");
        ctlMap.put("注册成功", "đăng ký thành công");
        ctlMap.put("注册失败", "đăng ký thất bại");

        Map<String, String> errMap = new HashMap<>();
        errMap.put("比赛已完成", "Trò chơi đã hoàn thành");
        errMap.put("别名不存在", "Bí danh không tồn tại");
        errMap.put("别名已存在", "Bí danh đã tồn tại");
        errMap.put("博币结算失败", "Thanh toán tiền bo không thành công");
        errMap.put("不允许编辑", "Không được phép chỉnh sửa");
        errMap.put("不允许删除", "Xóa không được phép");
        errMap.put("不支持此类型", "Loại này không được hỗ trợ");
        errMap.put("不支持的回复类型", "Loại trả lời không được hỗ trợ");
        errMap.put("不支持的状态变更", "Thay đổi trạng thái không được hỗ trợ");
        errMap.put("抽奖次数已用完", "Số lượt rút đã hết");
        errMap.put("抽奖次数异常", "Số lần rút thăm bất thường");
        errMap.put("抽奖活动未设置奖品", "Không có giải thưởng nào được đặt cho rút thăm trúng thưởng");
        errMap.put("此话题仅管理员可编辑", "Chủ đề này chỉ có thể được chỉnh sửa bởi quản trị viên");
        errMap.put("此帖已删除", "bài viết đã bị xóa");
        errMap.put("此投注项不属于当前竞猜", "Cược này không thuộc về cược hiện tại");
        errMap.put("存在多条记录", "Có nhiều bản ghi");
        errMap.put("存在重复天数", "Có những ngày trùng lặp");
        errMap.put("当前订单已审批", "Lệnh hiện tại đã được phê duyệt");
        errMap.put("对话回复必须指定父级", "Câu trả lời cuộc hội thoại phải chỉ định cha mẹ");
        errMap.put("发送次数过多", "Đã gửi quá nhiều lần");
        errMap.put("分类不存在", "Danh mục không tồn tại");
        errMap.put("更新后值为负", "Giá trị cập nhật là âm");
        errMap.put("更新数据为空", "Dữ liệu cập nhật trống");
        errMap.put("话题已关闭", "Chủ đề đã bị đóng");
        errMap.put("回复记录不存在", "Bản ghi trả lời không tồn tại");
        errMap.put("活动不存在", "Hoạt động không tồn tại");
        errMap.put("活动结束时间不能大于展示时间", "Thời gian kết thúc sự kiện không được lớn hơn thời gian hiển thị");
        errMap.put("活动开始时间必须小于结束时间", "Thời gian bắt đầu hoạt động phải nhỏ hơn thời gian kết thúc");
        errMap.put("活动开始时间不能小于展示时间", "Thời gian bắt đầu hoạt động không được nhỏ hơn thời gian hiển thị");
        errMap.put("活动类型异常", "Ngoại lệ loại hoạt động");
        errMap.put("活动名称已存在", "Tên sự kiện đã tồn tại");
        errMap.put("活动数据更新失败", "Cập nhật dữ liệu hoạt động không thành công");
        errMap.put("活动未到展示时间", "Sự kiện chưa đến thời gian hiển thị");
        errMap.put("活动未开启", "Sự kiện chưa được bắt đầu");
        errMap.put("活动未开始", "Sự kiện chưa bắt đầu");
        errMap.put("活动已过期", "Sự kiện đã hết hạn");
        errMap.put("活动已过展示时间", "Sự kiện đã hết hạn");
        errMap.put("活动已经开始", "Sự kiện đã bắt đầu");
        errMap.put("获取失败", "Không thể lấy được");
        errMap.put("记录不存在", "Bản ghi không tồn tại");
        errMap.put("奖品名称不能为空", "Tên giải thưởng không được để trống");
        errMap.put("奖品数量必须为正数", "Số lượng giải thưởng phải là số dương");
        errMap.put("奖品数量不能为空", "Số lượng giải thưởng không được để trống");
        errMap.put("奖品图片不能为空", "Hình ảnh giải thưởng không được để trống");
        errMap.put("角色标识已存在", "ID vai trò đã tồn tại");
        errMap.put("结算金额必须为正数", "Số tiền thanh toán phải dương");
        errMap.put("结算密码不正确", "Mật khẩu thanh toán không chính xác");
        errMap.put("今日已发送太多邮件", "Hôm nay đã gửi quá nhiều email");
        errMap.put("今日已签到", "Đã đăng nhập hôm nay");
        errMap.put("今天已兑换过", "Đã đổi hôm nay");
        errMap.put("金额异常", "Số tiền bất thường");
        errMap.put("竞猜记录不存在", "Hồ sơ cá cược không tồn tại");
        errMap.put("竞猜记录状态异常", "Trạng thái hồ sơ cá cược là bất thường");
        errMap.put("竞猜结束时间必须大于当前时间", "Thời gian kết thúc cá cược phải lớn hơn thời gian hiện tại");
        errMap.put("竞猜进行中", "Đang tiến hành cá cược");
        errMap.put("竞猜开始时间必须大于当前时间", "Thời gian bắt đầu đặt cược phải lớn hơn thời gian hiện tại");
        errMap.put("竞猜开始时间必须小于结束时间", "Thời gian bắt đầu đặt cược phải nhỏ hơn thời gian kết thúc");
        errMap.put("竞猜类型不存在", "Loại cá cược không tồn tại");
        errMap.put("竞猜类型未启用", "Loại cá cược không được kích hoạt");
        errMap.put("竞猜未开始", "Cuộc cá cược vẫn chưa bắt đầu");
        errMap.put("竞猜项名称重复", "Tên hạng mục cá cược trùng lặp");
        errMap.put("竞猜已关闭", "Cược đã đóng");
        errMap.put("竞猜已结束", "Cược đã kết thúc");
        errMap.put("旧密码不正确", "Mật khẩu cũ không đúng");
        errMap.put("开赛时间必须大于当前时间", "Thời gian bắt đầu phải lớn hơn thời gian hiện tại");
        errMap.put("扣减抽奖次数失败", "Không thể trừ số lần rút");
        errMap.put("库存不足", "Thiếu hàng tồn kho");
        errMap.put("库存更新失败", "Cập nhật hàng tồn kho không thành công");
        errMap.put("两次输入密码不一致", "Mật khẩu nhập hai lần không nhất quán");
        errMap.put("流不存在", "Luồng không tồn tại");
        errMap.put("论坛已关闭", "Diễn đàn đã đóng cửa");
        errMap.put("名称已存在", "Tên đã tồn tại");
        errMap.put("您的邮箱已完成验证", "Email của bạn đã được xác minh");
        errMap.put("排序值不符合要求", "Giá trị sắp xếp không đáp ứng yêu cầu");
        errMap.put("赔率必须大于等于壹", "Tỷ lệ cược phải lớn hơn hoặc bằng một");
        errMap.put("评论内容超出限制", "Nội dung bình luận vượt quá giới hạn");
        errMap.put("签到活动未设置奖品", "Không có giải thưởng nào được đặt cho sự kiện đăng nhập");
        errMap.put("签到天数不能大于活动天数", "Số ngày nhận phòng không thể lớn hơn số ngày hoạt động");
        errMap.put("签到天数不能为空", "Số ngày nhận phòng không được để trống");
        errMap.put("签到天数不能小于零", "Số ngày nhận phòng không được nhỏ hơn 0");
        errMap.put("钱包不存在", "Ví không tồn tại");
        errMap.put("请求参数格式错误", "Lỗi định dạng tham số yêu cầu");
        errMap.put("请求参数值格式非法", "Định dạng giá trị tham số yêu cầu là bất hợp pháp");
        errMap.put("请输入与当前不同的邮箱地址", "Vui lòng nhập địa chỉ email khác với địa chỉ hiện tại");
        errMap.put("上传失败", "Tải lên thất bại");
        errMap.put("生成结算备注异常", "Ngoại lệ trong việc tạo nhận xét giải quyết");
        errMap.put("数值异常", "Sự bất thường về số");
        errMap.put("投注项结算模式异常", "Chế độ giải quyết đặt cược bất thường");
        errMap.put("投注项结算状态异常", "Tình trạng thanh toán bất thường của các hạng mục đặt cược");
        errMap.put("投注项名称不能未空", "Tên mục đặt cược không được để trống");
        errMap.put("投注项名称重复", "Tên cược trùng lặp");
        errMap.put("投注项赔率倍数必须大于零", "Hệ số tỷ lệ cược của vật phẩm cá cược phải lớn hơn 0");
        errMap.put("投注项赔率必须大于", "Tỷ lệ cược của hạng mục đặt cược phải lớn hơn");
        errMap.put("投注项赔率不能小于一", "Tỷ lệ đặt cược không thể nhỏ hơn một");
        errMap.put("投注项已关闭", "Cược đã đóng");
        errMap.put("投注项状态异常", "Trạng thái của hạng mục cá cược là bất thường");
        errMap.put("下注金额异常", "Số tiền đặt cược bất thường");
        errMap.put("显示开始时间必须小于结束时间", "Thời gian bắt đầu hiển thị phải nhỏ hơn thời gian kết thúc");
        errMap.put("消息异常", "Ngoại lệ tin nhắn");
        errMap.put("新增失败", "Không thể thêm");
        errMap.put("选择双阵营时客队名称必填", "Cần phải có tên của đội khách khi chọn đội đôi.");
        errMap.put("选择双阵营时主队名称必填", "Khi chọn phe kép, bắt buộc phải có tên đội chủ nhà.");
        errMap.put("验证码不存在或者已过期", "Mã xác minh không tồn tại hoặc đã hết hạn");
        errMap.put("验证码错误", "Lỗi mã xác minh");
        errMap.put("邀请码不存在", "Mã mời không tồn tại");
        errMap.put("已被踢出", "đã bị đuổi ra ngoài");
        errMap.put("已存在同类型启用活动", "Một hoạt động kích hoạt cùng loại đã tồn tại");
        errMap.put("已关联竞猜", "Đã liên kết với cá cược");
        errMap.put("用户被禁言", "Người dùng bị cấm");
        errMap.put("用户编号为空", "ID người dùng trống");

        errMap.put("用户不在此房间", "Người dùng không có trong phòng này");
        errMap.put("用户当前改名卡次数异常", "Số thẻ thay đổi tên hiện tại của người dùng là bất thường.");
        errMap.put("用户名或者邮箱不存在", "Tên người dùng hoặc email không tồn tại");
        errMap.put("用户名已被注册", "Tên người dùng đã được đăng ký");
        errMap.put("用户钱包不存在", "Ví người dùng không tồn tại");
        errMap.put("用户钱包异常", "Ví người dùng bất thường");
        errMap.put("用户未设置密码", "Người dùng chưa đặt mật khẩu");
        errMap.put("用户未设置邮箱", "Người dùng chưa đặt địa chỉ email");
        errMap.put("邮件发送时间间隔过短", "Khoảng thời gian gửi email quá ngắn");
        errMap.put("邮箱地址已被注册", "Địa chỉ email đã được đăng ký");
        errMap.put("邮箱更新失败", "Cập nhật email không thành công");
        errMap.put("余额不足", "Thiếu cân bằng");
        errMap.put("暂无权限", "Chưa có sự cho phép");
        errMap.put("账号已被禁用", "Tài khoản đã bị vô hiệu hóa");
        errMap.put("阵营选择只允许", "Lựa chọn phe phái chỉ cho phép");
        errMap.put("支付失败", "Thanh toán không thành công");
        errMap.put("只有结算中状态支持撤销操作", "Chỉ trạng thái giải quyết mới hỗ trợ thao tác hoàn tác.");
        errMap.put("只有结算中状态支持结算操作", "Chỉ trạng thái thanh toán mới hỗ trợ hoạt động thanh toán.");
        errMap.put("中奖概率不能大于百分之百", "Xác suất chiến thắng không thể lớn hơn 100%");
        errMap.put("中奖概率不能小于零", "Xác suất thắng không thể nhỏ hơn 0");
        errMap.put("中奖总概率必须为百分之百", "Tổng xác suất thắng phải là 100%");
        errMap.put("重置密码失败", "Đặt lại mật khẩu không thành công");
        errMap.put("主键为空", "Khóa chính trống");
        errMap.put("状态异常", "Tình trạng bất thường");
        errMap.put("您无权权限,具体信息请联系客服","Bạn không có quyền, vui lòng liên hệ bộ phận chăm sóc khách hàng để biết thông tin cụ thể");
        errMap.put("无法编辑,您不是发布者/版主,或者帖子被锁定","Không thể chỉnh sửa, bạn không phải là nhà xuất bản/người kiểm duyệt hoặc bài viết bị khóa");
        errMap.put("无法删除,您不是发布者/版主,或者帖子被锁定","Không thể xóa, bạn không phải là nhà xuất bản/người kiểm duyệt hoặc bài viết bị khóa");

        Map<String, String> bizMap = new HashMap<>();
        bizMap.put("博民网", "BoMinWang");
        bizMap.put("不在投放时间范围", "Không nằm trong phạm vi thời gian giao hàng");
        bizMap.put("待定", "Để được xác định");
        bizMap.put("登录赠送博币", "Đăng nhập để nhận xu thưởng");
        bizMap.put("点球", "Đá phạt");
        bizMap.put("点赞成功", "Like thành công");
        bizMap.put("发表回帖/回复获得博币", "Đăng bài trả lời/trả lời để nhận xu Bo");
        bizMap.put("狗庄揭秘", "Làng chó được tiết lộ");
        bizMap.put("管理员扣减博币", "Quản trị viên trừ tiền cờ bạc");
        bizMap.put("管理员扣减经验", "Khấu trừ kinh nghiệm của quản trị viên");
        bizMap.put("管理员增加博币", "Quản trị viên tăng tiền cờ bạc");
        bizMap.put("管理员增加经验", "Quản trị viên tích lũy kinh nghiệm");
        bizMap.put("广告未启用", "Quảng cáo chưa được bật");
        bizMap.put("行业资讯", "Thông tin ngành");
        bizMap.put("加时", "Tăng ca");
        bizMap.put("篮球", "bóng rổ");
        bizMap.put("每日活跃奖励", "Phần thưởng hoạt động hàng ngày");
        bizMap.put("每日首帖奖励", "Phần thưởng đăng bài đầu tiên hàng ngày");
        bizMap.put("请求参数数据大小超过限制", "Kích thước dữ liệu tham số yêu cầu vượt quá giới hạn");
        bizMap.put("上半场", "nửa đầu");
        bizMap.put("收藏成功", "Bộ sưu tập thành công");
        bizMap.put("首次上传头像奖励", "Phần thưởng upload avatar lần đầu");
        bizMap.put("帖子被回复获得博币", "Nhận BoCoin khi bài đăng của bạn được phản hồi");
        bizMap.put("帖子加精华", "Bài viết có điểm nổi bật");
        bizMap.put("帖子上热门", "Bài viết phổ biến");
        bizMap.put("投放中", "Đang giao hàng");
        bizMap.put("推迟", "hoãn");
        bizMap.put("推流", "Đẩy phát trực tuyến");
        bizMap.put("完", "qua");
        bizMap.put("未开赛", "Chưa bắt đầu");
        bizMap.put("未推流", "Không bị đẩy");
        bizMap.put("无", "không có");
        bizMap.put("验证邮箱奖励", "Xác minh phần thưởng email");
        bizMap.put("腰斩", "cắt một nửa");
        bizMap.put("已取消点赞", "Đã hủy lượt thích");
        bizMap.put("已取消收藏", "Đã hủy yêu thích");
        bizMap.put("已完场", "Hoàn thành");
        bizMap.put("有", "có");
        bizMap.put("中半场", "nửa giữa");
        bizMap.put("中场", "tiền vệ");
        bizMap.put("中断", "ngắt");
        bizMap.put("注册赠送博币", "Đăng ký và nhận xu Bo miễn phí");
        bizMap.put("足球", "bóng đá");
        // ------
        bizMap.put("恭喜您获得", "Chúc mừng bạn đã nhận được");
        bizMap.put("获得博币", "Nhận tiền đánh bạc");
        bizMap.put("获得经验", "trải nghiệm");
        bizMap.put("今天", "Hôm nay");
        bizMap.put("竞猜状态异常", "Trạng thái cá cược là bất thường");
        bizMap.put("连续签到", "Đăng nhập liên tục");
        bizMap.put("您暂未中奖,再接再厉哦～", "Bạn vẫn chưa thắng, vui lòng thử lại~");
        bizMap.put("普通签到", "Đăng nhập thông thường");
        bizMap.put("签到成功", "Đăng nhập thành công");
        bizMap.put("商城兑换", "Trao đổi trung tâm mua sắm");
        bizMap.put("未中奖", "Không thắng");
        bizMap.put("转盘抽奖", "xổ số Roulette");
        bizMap.put("周", "tuần");
        bizMap.put("天", "bầu trời");

        // 类型, 1:ctl 2:svs-err 3:svs-biz
        addBatch(ctlMap, 1);
        addBatch(errMap, 2);
        addBatch(bizMap, 3);*/
    }

    private void addBatch(Map<String, String> errMap, int type) {
        Date date = new Date();
        List<TDictI18n> list = new ArrayList<>();
        Set<Map.Entry<String, String>> entries = errMap.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            TDictI18n dictI18n = new TDictI18n();
            dictI18n.setLanguage("vi");
            dictI18n.setType(type);
            dictI18n.setZhCode(entry.getKey());
            dictI18n.setTranslation(entry.getValue());
            dictI18n.setStatus(1);
            dictI18n.setCreateDate(date);
            list.add(dictI18n);
        }
        for (TDictI18n item : list) {
            tDictI18nMapper.insertSelective(item);
        }
    }

    @Override
    public List<String> check() {
        List<TDictI18n> list = findByLanguage(I18nUtil.LANGUAGE);
        Set<String> set = new HashSet<>();
        List<String> result = new ArrayList<>();
        for (TDictI18n item : list) {
            String zhCode = item.getZhCode();
            if (set.contains(zhCode)) {
                result.add(zhCode);
            } else {
                set.add(zhCode);
            }
        }
        return result;
    }
}

package org.jeecg.common.constant;

/**
 * @Author Rade
 * @Date 2021/3/26 15:25:25
 * @Description 审核状态
 */
public interface ExamineStateConstants {
    /**
     * 待审核
     */
    String TO_BE_REVIEWED = "1";

    /**
     * 审核通过
     */
    String APPROVED = "2";

    /**
     * 审核不通过
     */
    String FAIL_TO_AUDIT = "3";

    /**
     * 待上链
     */
    String TO_BE_CHAIN = "4";

    /**
     * 上链中
     */
    String CHAINING = "5";

    /**
     * 已上链
     */
    String CHAINED = "6";
}

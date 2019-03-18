package top.lrshuai.ems.dto;

import lombok.Data;

@Data
public class EMSQueryDTO {
    /**
     * 输出语言(output language)默认CN，可选EN(英文)JA（日文）KO（韩文）FR（法文）RU（俄文）PT（葡萄牙文）ES（西班牙文）VI（越南文）DE（德文）AR（阿拉伯文）ID（印尼文）
     */
    private String lg;
    /**
     * 快递单号（express number）【顺丰请输入单号 : 收件人或寄件人手机号后四位。例如：123456789:1234】
     */
    private String no;
    /**
     * 快递商类型(express company type)
     */
    private String type;
}

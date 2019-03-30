package org.yzh.web.jt808.dto;

import org.yzh.framework.annotation.Property;
import org.yzh.framework.enums.DataType;
import org.yzh.framework.message.PackageData;
import org.yzh.web.jt808.dto.basics.Header;

/**
 * 摄像头立即拍摄命令应答
 */
public class CameraShotReply extends PackageData<Header> {

    private Integer flowId;
    private Integer result;
    private Integer total;
    private byte[] idList;

    public CameraShotReply() {
    }

    @Property(index = 0, type = DataType.WORD, desc = "应答流水号")
    public Integer getFlowId() {
        return flowId;
    }

    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    @Property(index = 2, type = DataType.BYTE, desc = "结果")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Property(index = 2, type = DataType.WORD, desc = "多媒体ID个数")
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Property(index = 4, type = DataType.BYTES, desc = "多媒体ID列表")
    public byte[] getIdList() {
        return idList;
    }

    public void setIdList(byte[] idList) {
        this.idList = idList;
    }
}
package org.yzh.web.jt808.dto;

import org.yzh.framework.annotation.Property;
import org.yzh.framework.enums.DataType;
import org.yzh.framework.message.PackageData;
import org.yzh.web.jt808.dto.basics.Header;

/**
 * 终端注册消息
 */
public class Register extends PackageData<Header> {

    private Integer provinceId;
    private Integer cityId;
    private String manufacturerId;
    private String terminalType;
    private String terminalId;
    private Integer licensePlateColor;
    private String licensePlate;

    @Property(index = 0, type = DataType.WORD, desc = "省域ID,设备安装车辆所在的省域,省域ID采用GB/T2260中规定的行政区划代码6位中前两位")
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Property(index = 2, type = DataType.WORD, desc = "市县域ID,设备安装车辆所在的市域或县域,市县域ID采用GB/T2260中规定的行政区划代码6位中后四位")
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Property(index = 4, type = DataType.STRING, length = 5, pad = 32, desc = "制造商ID,终端制造商编码")
    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Property(index = 9, type = DataType.STRING, length = 8, pad = 32, desc = "终端型号,由制造商自行定义,位数不足八位补空格")
    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    @Property(index = 17, type = DataType.STRING, length = 7, desc = "终端ID,由大写字母和数字组成,此终端ID由制造商自行定义")
    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    @Property(index = 24, type = DataType.BYTE, desc = "车牌颜色,按照JT/T415-2006的5.4.12（0:未上车牌,1:蓝色,2:黄色,3:黑色,4:白色,9:其他）")
    public Integer getLicensePlateColor() {
        return licensePlateColor;
    }

    public void setLicensePlateColor(Integer licensePlate) {
        this.licensePlateColor = licensePlate;
    }

    @Property(index = 25, type = DataType.STRING, desc = "车牌 公安交通管理部门颁发的机动车号牌")
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

}
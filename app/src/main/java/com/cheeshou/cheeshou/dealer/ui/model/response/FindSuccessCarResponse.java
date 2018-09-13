package com.cheeshou.cheeshou.dealer.ui.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.nostra13.universalimageloader.utils.L;

import java.util.List;

public class FindSuccessCarResponse implements Parcelable {
    private int code;
    private List<DataBean> data;
    private String msg;

    protected FindSuccessCarResponse(Parcel in) {
        code = in.readInt();
        msg = in.readString();
    }

    public static final Creator<FindSuccessCarResponse> CREATOR = new Creator<FindSuccessCarResponse>() {
        @Override
        public FindSuccessCarResponse createFromParcel(Parcel in) {
            return new FindSuccessCarResponse(in);
        }

        @Override
        public FindSuccessCarResponse[] newArray(int size) {
            return new FindSuccessCarResponse[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(msg);
    }

    public static class DataBean implements Parcelable {
        private String brand;
        private String carSeries;
        private String cityName;
        private String imgThumUrl;
        private String orderPrice;
        private String provinceName;
        private String vname;
        private boolean checked;
        private boolean hideCheck;

        protected DataBean(Parcel in) {
            brand = in.readString();
            carSeries = in.readString();
            cityName = in.readString();
            imgThumUrl = in.readString();
            orderPrice = in.readString();
            provinceName = in.readString();
            vname = in.readString();
            checked = in.readByte() != 0;
            hideCheck = in.readByte() != 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getCarSeries() {
            return carSeries;
        }

        public void setCarSeries(String carSeries) {
            this.carSeries = carSeries;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getImgThumUrl() {
            return imgThumUrl;
        }

        public void setImgThumUrl(String imgThumUrl) {
            this.imgThumUrl = imgThumUrl;
        }

        public String getOrderPrice() {
            return orderPrice;
        }

        public void setOrderPrice(String orderPrice) {
            this.orderPrice = orderPrice;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        public boolean isHideCheck() {
            return hideCheck;
        }

        public void setHideCheck(boolean hideCheck) {
            this.hideCheck = hideCheck;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(brand);
            dest.writeString(carSeries);
            dest.writeString(cityName);
            dest.writeString(imgThumUrl);
            dest.writeString(orderPrice);
            dest.writeString(provinceName);
            dest.writeString(vname);
            dest.writeByte((byte) (checked ? 1 : 0));
            dest.writeByte((byte) (hideCheck ? 1 : 0));
        }
    }
}

package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.game.core.entity.BoundingBox;
import com.game.core.entity.BoundingBoxDO;
import com.game.core.entity.CitySeat;
import com.game.core.entity.WGS84Point;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhaolianjie
 * @date 2023年03月02日 14:57
 */
public class RegionalBlocking {


    /**
     * 地球半径
     **/
    private static final double R = 6371e3;
    /**
     * 180°
     **/
    private static final DecimalFormat df = new DecimalFormat("0.000000");


    private static double EARTH_RADIUS = 6371000;//赤道半径(单位m)

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }


    /**
     * 经度 -- Longitude
     * 维度 -- Latitude
     * 中国 -- 东经73.78333 - 134.083333
     * -- 北纬3.616667 - 53.550000
     *
     * @return
     */
    public static List<BoundingBox> regionalBlocking(double southLatitude, double northLatitude, double westLongitude, double eastLongitude
            , Integer blockNum) {
        BoundingBox boundingBox = new BoundingBox(new WGS84Point(southLatitude, westLongitude), new WGS84Point(northLatitude, eastLongitude));

        double latNum = boundingBox.getLatitudeSize();
        double longNum = boundingBox.getLongitudeSize();
        double latOneNum = latNum / blockNum;
        BigDecimal valueDecimal = new BigDecimal(String.valueOf(latOneNum));
        latOneNum = valueDecimal.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

        double longOneNum = longNum / blockNum;
        BigDecimal valueDecimal1 = new BigDecimal(longOneNum);
        longOneNum = valueDecimal1.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();

        List<BoundingBox> list = new ArrayList<>();
        for (int i = 0; i < blockNum; i++) {
            double latitudeThis = BigDecimal.valueOf(southLatitude + (i * latOneNum)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            ;
            double latitudeNext = BigDecimal.valueOf(southLatitude + ((i + 1) * latOneNum)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
            ;
            for (int j = 0; j < blockNum; j++) {
                BoundingBox boundingBox1 = new BoundingBox(new WGS84Point(latitudeThis, westLongitude + j * longOneNum)
                        , new WGS84Point(latitudeNext, westLongitude + (j + 1) * longOneNum));
                list.add(boundingBox1);
            }
        }
        return list;
    }


    /**
     * @param lon1 第一点的精度
     * @param lat1 第一点的纬度
     * @param lon2 第二点的精度
     * @param lat2 第二点的纬度
     * @return 返回的距离，单位m
     */
    public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }


    public static void main(String[] args) {
//        List<BoundingBox> list = regionalBlocking(3.616667, 53.550000, 73.78333, 134.083333, 5000);
//        double longitude = 114.35162;
//        double latitude = 30.37500;
//        System.out.println(list.size());
//        BoundingBox boundingBox1 = null;
//        // 1
//        for (BoundingBox boundingBox : list) {
//            if (boundingBox.getSouthLatitude() < latitude && boundingBox.getNorthLatitude() > latitude
//                    && boundingBox.getWestLongitude() < longitude && boundingBox.getEastLongitude() > longitude) {
//                boundingBox1 = boundingBox;
//            }
//        }
        // 2
        //System.out.println(GetDistance(boundingBox1.getEastLongitude(), boundingBox1.getNorthLatitude(), boundingBox1.getWestLongitude(), boundingBox1.getSouthLatitude()));

        //3
//        Double[] result = calLocationByDistanceAndLocationAndDirection(45, 114.31, 30.52, 30000);
//        Double[] result2 = calLocationByDistanceAndLocationAndDirection(225, 114.31, 30.52, 30000);
//        System.out.print(result[0] + ",");
//        System.out.println(result[1]);
//        System.out.print(result2[0] + ",");
//        System.out.println(result2[1]);
//
//        double longitude = 114.35162;
//        double latitude = 30.37500;
//
//
//        BoundingBox boundingBox = new BoundingBox(new WGS84Point(3.616667, 73.78333), new WGS84Point(53.550000, 134.083333));
//
//        BoundingBox boundingBoxw = new BoundingBox(new WGS84Point(result2[1], result2[0]), new WGS84Point(result[1], result[0]));
//
//        Double[] result3 = calLocationByDistanceAndLocationAndDirection(45, 114.31, 30.52, 3000);
//        Double[] result4 = calLocationByDistanceAndLocationAndDirection(225, 114.31, 30.52, 3000);
//
//        BoundingBox boundingBox1 = new BoundingBox(new WGS84Point(result4[1], result4[0]), new WGS84Point(result3[1], result3[0]));
//
//        if (boundingBox.contains(new WGS84Point(latitude, longitude))) {
//            System.out.println(true);
//        }
//        if (boundingBox.intersects(boundingBoxw)) {
//            System.out.println(true);
//        }

        //System.out.println(getBoundingBoxConfig("C:\\Users\\123456\\Desktop\\区域划分.xls"));
        buildBoundingBoxDO();


    }


    public static List<BoundingBoxDO> buildBoundingBoxDO() {
        List<BoundingBoxDO> resultList = new ArrayList<>();
        List<BoundingBox> list = regionalBlocking(28.52, 35.52, 107.31, 120.31, 200);
        System.out.println(list.size());
        List<CitySeat> configList = getBoundingBoxConfig("C:\\Users\\123456\\Desktop\\区域划分.xls");

        for (BoundingBox boundingBox : list) {
            BoundingBoxDO boundingBoxDO = new BoundingBoxDO();
            boundingBoxDO.setSouthLatitude(boundingBox.getSouthLatitude());
            boundingBoxDO.setNorthLatitude(boundingBox.getNorthLatitude());
            boundingBoxDO.setEastLongitude(boundingBox.getEastLongitude());
            boundingBoxDO.setWestLongitude(boundingBox.getWestLongitude());
            for (CitySeat citySeat : configList) {
                if (citySeat.getBoundingBox().contains(boundingBox.getCenter())) {
                    boundingBoxDO.setProvince(citySeat.getProvice());
                    boundingBoxDO.setCity(citySeat.getCity());
                    System.out.println(JSON.toJSONString(boundingBoxDO));
                }
            }
            resultList.add(boundingBoxDO);
        }
        return resultList;
    }

    /**
     * 根据配置 - 获取 城市对应的区块
     *
     * @param filePath
     * @return
     */
    public static List<CitySeat> getBoundingBoxConfig(String filePath) {
        List<CitySeat> citySeats = ReadExcelUtils.getConfig(filePath);
        return citySeats.stream().map(citySeat -> {
            BoundingBox boundingBox = buildBoundingBoxWithCityPointAndDistance(citySeat.getLongitude(), citySeat.getLatitude(), citySeat.getDistance());
            citySeat.setBoundingBox(boundingBox);
            System.out.println(JSONObject.toJSONString(citySeat));
            return citySeat;
        }).collect(Collectors.toList());
    }

    /**
     * 根据一点的经纬度 算出一个区块
     *
     * @param startLong
     * @param startLat
     * @param distance
     * @return
     */
    private static BoundingBox buildBoundingBoxWithCityPointAndDistance(double startLong, double startLat, double distance) {
        Double[] result = calLocationByDistanceAndLocationAndDirection(45, startLong, startLat, distance);
        Double[] result2 = calLocationByDistanceAndLocationAndDirection(225, startLong, startLat, distance);
        return new BoundingBox(new WGS84Point(result2[1], result2[0]), new WGS84Point(result[1], result[0]));

    }

    /**
     * 根据一点的坐标与距离，以及方向，计算另外一点的位置
     *
     * @param angle     角度，从正北顺时针方向开始计算
     * @param startLong 起始点经度
     * @param startLat  起始点纬度
     * @param distance  距离，单位m
     * @return
     */
    public static Double[] calLocationByDistanceAndLocationAndDirection(double angle, double startLong, double startLat, double distance) {
        Double[] result = new Double[2];
        //将距离转换成经度的计算公式
        double δ = distance / R;
        // 转换为radian，否则结果会不正确
        angle = Math.toRadians(angle);
        startLong = Math.toRadians(startLong);
        startLat = Math.toRadians(startLat);
        double lat = Math.asin(Math.sin(startLat) * Math.cos(δ) + Math.cos(startLat) * Math.sin(δ) * Math.cos(angle));
        double lon = startLong + Math.atan2(Math.sin(angle) * Math.sin(δ) * Math.cos(startLat), Math.cos(δ) - Math.sin(startLat) * Math.sin(lat));
        // 转为正常的10进制经纬度
        lon = Math.toDegrees(lon);
        lat = Math.toDegrees(lat);
        result[0] = lon;
        result[1] = lat;
        return result;
    }


}

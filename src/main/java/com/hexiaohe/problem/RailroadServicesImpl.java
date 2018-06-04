package com.hexiaohe.problem;

import com.hexiaohe.problem.graphUtil.Graph;
import com.hexiaohe.problem.graphUtil.Vertex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * RailroadServices的实现类，实现RailroadServices对外提供的方法，也继承了Graph类
 *
 * @author HeHaoming
 * @version 1.0
 */

public class RailroadServicesImpl extends Graph implements RailroadServices {
    private static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";


    /**
     * 增加一条从start到end的路线，其距离为distance
     *
     * @param start    起点
     * @param end      终点
     * @param distance 从起点到终点的距离
     */

    @Override
    public void addTrack(Character start, Character end, Integer distance) {
        Vertex vertex = new Vertex(end, distance);
        //顶点不存在
        if (!super.getVertices().containsKey(start)) {
            super.getVertices().put(start, new ArrayList<Vertex>(Arrays.asList(vertex)));
        }
        //顶点存在
        else {
            List<Vertex> edge = super.getVertices().get(start);
            edge.add(vertex);
        }
    }

    /**
     * 获取一条确定路线的距离
     *
     * @param track 一条由站点组成的路线。
     *              eg:"A-B-C" 表示了从 A 到 C 的路线
     * @return 这条路线的长度，如不可达返回"NO SUCH ROUTE"
     */

    @Override
    public String getDistanceOfCertainTrack(String track) {
        String replace = track.replace("-", "");
        char[] chars = replace.toCharArray();
        //获取路线的距离
        Integer distanceOfPath = super.getDistanceOfPath(chars);
        //路线存在不可达站点
        if (distanceOfPath == null) {
            return NO_SUCH_ROUTE;
        }
        //返回路线长度
        else {
            return distanceOfPath + "";
        }
    }

    /**
     * 获取两个站点间经过的站点数不超过maxStop的路线数
     *
     * @param start   起点
     * @param end     终点
     * @param maxStop 经过的最大站点数
     * @return 所有满足条件的路线数，不存在返回"NO SUCH ROUTE"
     */

    @Override
    public String getTracksCountOfMaximumStop(Character start, Character end, Integer maxStop) {
        //获取经过站点数不超过maxStop的所有路线，包括从自己到自己（起点和终点相同）
        List<String> tracks = super.DFSByCertainDepth(maxStop, start, end);
        //过滤掉从自己到自己的路线
        List<String> tracksList = tracks.stream().filter(track -> track.length() > 1).collect(Collectors.toList());
        //没有符合条件的路线
        if (tracksList.isEmpty()) {
            return NO_SUCH_ROUTE;
        }
        //返回路线数
        else {
            return tracksList.size() + "";
        }
    }

    /**
     * 获取两个站点间经过的站点数恰好是certainStop的路线数
     *
     * @param start       起点
     * @param end         终点
     * @param certainStop 经过的站点数
     * @return 所有满足条件的路线数，不存在返回"NO SUCH ROUTE"
     */

    @Override
    public String getTracksCountOfCertainStop(Character start, Character end, Integer certainStop) {
        //获取经过站点数不超过certainStop的所有路线
        List<String> tracks = super.DFSByCertainDepth(certainStop, start, end);
        //过滤掉所有不满足经过站点数为certainStop的所有路线
        List<String> tracksList = tracks.stream().filter(track -> track.length() == certainStop + 1).collect(Collectors.toList());
        //没有符合条件的路线
        if (tracksList.isEmpty()) {
            return NO_SUCH_ROUTE;
        }
        //返回路线数
        else {
            return tracksList.size() + "";
        }
    }

    /**
     * 获取两个站点间的最短距离
     *
     * @param start 起点
     * @param end   终点
     * @return 返回最短距离，如不可达返回"NO SUCH ROUTE"
     */

    @Override
    public String getLengthOfShortestRoute(Character start, Character end) {
        //返回两站点间的最短距离
        Integer shortestPathDistance = super.getShortestPathDistance(start, end);
        //不可达
        if (shortestPathDistance == null) {
            return NO_SUCH_ROUTE;
        }
        //返回最短距离
        else {
            return shortestPathDistance + "";
        }
    }


    /**
     * 获取两个站点间距离小于maxDistance所有路线的总数
     *
     * @param start       起点
     * @param end         终点
     * @param maxDistance 最大距离
     * @return 满足条件的路线数，如不存在返回"NO SUCH ROUTE"
     */

    @Override
    public String getDifferentRoutesOfCertainTrack(Character start, Character end, Integer maxDistance) {
        //获取经过站点数不超过maxDistance的所有路线
        List<String> tracks = super.DFSByCertainDepth(maxDistance, start, end);
        List<String> tracksList = tracks.stream().filter(track -> {
            char[] trackArray = track.toCharArray();
            //每条路线的长度
            Integer distanceOfPath = super.getDistanceOfPath(trackArray);
            //过滤点路线长度大于30的路线以及从自己到自己的路线（起点和终点相同）
            return track.length() > 1 && distanceOfPath < 30;
        }).collect(Collectors.toList());
        //不存在
        if (tracksList.isEmpty()) {
            return NO_SUCH_ROUTE;
        }
        //路线数
        else {
            return tracksList.size() + "";
        }
    }

}

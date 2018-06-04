package com.hexiaohe.problem;

import org.junit.Before;
import org.junit.Test;

public class RailroadServiceTest {

    /*Test Result:
     * Output #1:9
     * Output #2:5
     * Output #3:13
     * Output #4:22
     * Output #5:NO SUCH ROUTE
     * Output #10:7
     * Output #7:3
     * Output #8:9
     * Output #9:9
     * Output #6:2
     * */
    private RailroadServices railroadServices;

    @Before
    public void init() {
        railroadServices = new RailroadServicesImpl();
        railroadServices.addTrack('A', 'B', 5);
        railroadServices.addTrack('B', 'C', 4);
        railroadServices.addTrack('C', 'D', 8);
        railroadServices.addTrack('D', 'C', 8);
        railroadServices.addTrack('D', 'E', 6);
        railroadServices.addTrack('A', 'D', 5);
        railroadServices.addTrack('C', 'E', 2);
        railroadServices.addTrack('E', 'B', 3);
        railroadServices.addTrack('A', 'E', 7);
    }

    @Test
    public void getDistanceOfCertainTrackTest1() {
        String distanceOfCertainTrack = railroadServices.getDistanceOfCertainTrack("A-B-C");
        System.out.println("Output #1:" + distanceOfCertainTrack);
    }

    @Test
    public void getDistanceOfCertainTrackTest2() {
        String distanceOfCertainTrack = railroadServices.getDistanceOfCertainTrack("A-D");
        System.out.println("Output #2:" + distanceOfCertainTrack);


    }

    @Test
    public void getDistanceOfCertainTrackTest3() {
        String distanceOfCertainTrack = railroadServices.getDistanceOfCertainTrack("A-D-C");
        System.out.println("Output #3:" + distanceOfCertainTrack);

    }

    @Test
    public void getDistanceOfCertainTrackTest4() {
        String distanceOfCertainTrack = railroadServices.getDistanceOfCertainTrack("A-E-B-C-D");
        System.out.println("Output #4:" + distanceOfCertainTrack);

    }

    @Test
    public void getDistanceOfCertainTrackTest5() {
        String distanceOfCertainTrack = railroadServices.getDistanceOfCertainTrack("A-E-D");
        System.out.println("Output #5:" + distanceOfCertainTrack);

    }

    @Test
    public void getTracksCountOfMaximumStopTest() {
        String tracksCountOfMaximumStop = railroadServices.getTracksCountOfMaximumStop('C', 'C', 3);
        System.out.println("Output #6:" + tracksCountOfMaximumStop);
    }

    @Test
    public void getTracksCountOfCertainStopTest() {
        String tracksCountOfCertainStop = railroadServices.getTracksCountOfCertainStop('A', 'C', 4);
        System.out.println("Output #7:" + tracksCountOfCertainStop);
    }

    @Test
    public void getLengthOfShortestRouteTest1() {
        String lengthOfShortestRoute = railroadServices.getLengthOfShortestRoute('A', 'C');
        System.out.println("Output #8:" + lengthOfShortestRoute);
    }

    @Test
    public void getLengthOfShortestRouteTest2() {
        String lengthOfShortestRoute = railroadServices.getLengthOfShortestRoute('B', 'B');
        System.out.println("Output #9:" + lengthOfShortestRoute);
    }

    @Test
    public void getDifferentRoutesOfCertainTrackTest() {
        String differentRoutesOfCertainTrack = railroadServices.getDifferentRoutesOfCertainTrack('C', 'C', 30);
        System.out.println("Output #10:" + differentRoutesOfCertainTrack);
    }
}

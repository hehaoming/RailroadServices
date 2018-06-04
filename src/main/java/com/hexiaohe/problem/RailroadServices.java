package com.hexiaohe.problem;

/**
 * The RailroadServices interface provides external standard access method.
 *
 * @author HeHaoming
 * @version 1.0
 */

public interface RailroadServices {

    /**
     * Add a route through Starting station, terminal station, distance
     *
     * @param start    The Starting station
     * @param end      The terminal station
     * @param distance The distance of the route start-end
     */
    void addTrack(Character start, Character end, Integer distance);

    /**
     * Get the length of a route
     *
     * @param track A route consisting of station.
     *              eg:"A-B-C" represents the route from A to C
     * @return the total length of the route
     */
    String getDistanceOfCertainTrack(String track);

    /**
     * Get the total number of routes less than maxStop from start to finish
     *
     * @param start   The Starting station
     * @param end     The terminal station
     * @param maxStop The maximum stops
     * @return the total number of routes
     */
    String getTracksCountOfMaximumStop(Character start, Character end, Integer maxStop);

    /**
     * Get the total number of routes with exactly certainStop stops from start to end
     *
     * @param start       The Starting station
     * @param end         The terminal station
     * @param certainStop The certain stops
     * @return the total number of routes
     */

    String getTracksCountOfCertainStop(Character start, Character end, Integer certainStop);

    /**
     * Get the length of shortest routes from start to finish
     *
     * @param start The Starting station
     * @param end   The terminal station
     * @return the length of shortest routes
     */
    String getLengthOfShortestRoute(Character start, Character end);

    /**
     * Get the total number of routes which length is less than maxDistance from start to finish
     *
     * @param start       The Starting station
     * @param end         The terminal station
     * @param maxDistance The maximum length
     * @return the total number of routes
     */
    String getDifferentRoutesOfCertainTrack(Character start, Character end, Integer maxDistance);
}

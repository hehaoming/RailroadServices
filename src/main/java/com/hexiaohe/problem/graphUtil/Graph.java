package com.hexiaohe.problem.graphUtil;

import java.util.*;

/**
 * 图的是实现，工具类，包括建图，最短路径，深度优先遍历
 *
 * @author HeHaoming
 * @version 1.0
 */

public class Graph {

    //用map存储图的结构，key表示每个顶点，List<Vertex>是邻接边集
    private final Map<Character, List<Vertex>> vertices;

    public Graph() {
        //用 HashMap 存储图
        this.vertices = new HashMap<Character, List<Vertex>>();
    }

    /**
     * 添加一个顶点及它的邻接边
     *
     * @param character 顶点
     * @param vertex    邻接表列表
     */
    public void addVertex(Character character, List<Vertex> vertex) {
        this.vertices.put(character, vertex);
    }

    /**
     * 获取两点间的最短距离，起点和终点可以相同
     *
     * @param start  起点
     * @param finish 终点
     * @return 起点和终点间的最短距离，不可达返回null
     */

    public Integer getShortestPathDistance(Character start, Character finish) {
        //最短距离
        Integer shortestPathDistance;
        //如果起点和终点不同
        if (start != finish) {
            shortestPathDistance = this.getShortestPath(start, finish);
        }
        //起点和终点相同
        else {
            //起点临界点中距离最近的
            Character newStart = start;
            //起点到临接点的最小距离
            int minDistance = Integer.MAX_VALUE;
            //起点临接点列表
            List<Vertex> neighbourList = this.vertices.get(start);
            //没有邻接点，返回不可达
            if (neighbourList.isEmpty()) {
                return null;
            }

            //有邻接点，选择距离起点最近的点作为起点
            for (Vertex vertex : neighbourList) {
                if (vertex.getDistance() < minDistance) {
                    minDistance = vertex.getDistance();
                    newStart = vertex.getId();
                }
            }
            //得到离起点最近邻接点到终点的距离再加上原起点到当前起点的最短距离
            shortestPathDistance = this.getShortestPath(newStart, finish) + minDistance;
        }

        return shortestPathDistance;
    }

    /**
     * 获取两点间的最短距离，起点和终点不可以相同，采用了Dijkstra算法
     *
     * @param start  起点
     * @param finish 终点
     * @return 起点和终点间的最短距离，不可达返回null
     */

    public Integer getShortestPath(Character start, Character finish) {

        //保存起点到各个结点的最短距离
        final Map<Character, Integer> distances = new HashMap<Character, Integer>();
        //采用优先队列存储节点，每次弹出距离最近的结点
        PriorityQueue<Vertex> nodes = new PriorityQueue<Vertex>();

        for (Character vertex : vertices.keySet()) {
            if (vertex == start) {
                //到自己的距离设置为0
                distances.put(vertex, 0);
                //保证第一个弹出起点
                nodes.add(new Vertex(vertex, 0));
            } else {
                //到其他节点的距离设置为无穷大
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.add(new Vertex(vertex, Integer.MAX_VALUE));
            }
        }
        while (!nodes.isEmpty()) {
            //弹出一个结点，第一次弹出起点，之后动态修改优先队列中结点
            Vertex smallest = nodes.poll();
            //如果弹出的结点是终点
            if (smallest.getId() == finish) {
                Integer distance = distances.get(smallest.getId());
                //如果对应的距离为无穷大，则返回不可达
                if (distance == Integer.MAX_VALUE) {
                    return null;
                }
                return distance;
            }
            //当前节点不可达
            if (distances.get(smallest.getId()) == Integer.MAX_VALUE) {
                break;
            }
            //neighbor是当前结点的相邻结点
            for (Vertex neighbor : vertices.get(smallest.getId())) {
                //对每一个相邻节点距离做出修改，用当前结点到起点的距离加上当前结点到相邻节点的距离
                Integer alt = distances.get(smallest.getId()) + neighbor.getDistance();
                //如果通过当前节点到达小于之前的路径，则修改为当前路径
                if (alt < distances.get(neighbor.getId())) {
                    //更新起点到当前节点的距离
                    distances.put(neighbor.getId(), alt);
                    //更新优先队列中结点的距离
                    for (Vertex n : nodes) {
                        if (n.getId() == neighbor.getId()) {
                            nodes.remove(n);
                            n.setDistance(alt);
                            nodes.add(n);
                            break;
                        }
                    }
                }
            }
        }

        return null;
    }


    /**
     * 获取一条路径的长度
     *
     * @param vertex 由站点组成路径
     * @return 路径的长度，不可达返回null
     */

    public Integer getDistanceOfPath(char... vertex) {
        //路径长度
        Integer distance = null;
        //保存前驱结点
        Character pre = null;
        //路径上超过两个结点，将长度置为 0
        if (vertex.length > 1) {
            distance = 0;
        }
        for (Character current : vertex) {
            //有前驱结点计算当前节点和前驱结点的距离，并累加
            if (pre != null) {
                Integer distanceBetweenTwoVertex = this.getDistanceBetweenTwoVertex(pre, current);
                //两点之间不可达，则整条路径不可达
                if (distanceBetweenTwoVertex == null) {
                    return null;
                } else {
                    distance += distanceBetweenTwoVertex;
                }
            }
            pre = current;
        }
        return distance;
    }

    /**
     * 获取两点间的距离
     *
     * @param pre     起点
     * @param current 终点
     * @return 路径的长度，不可达返回null
     */

    private Integer getDistanceBetweenTwoVertex(Character pre, Character current) {
        //找到起点对应的邻接点链表
        List<Vertex> vertexList = this.vertices.get(pre);
        Integer distance = null;
        for (Vertex vertex : vertexList) {
            //如果邻接点是终点，则保存距离，退出循环
            if (vertex.getId() == current) {
                distance = vertex.getDistance();
                break;
            }
        }
        return distance;
    }


    /**
     * 内部内，用来保存一个顶点及他的未访问的邻接点链表，以及顶点本身的访问状态
     *
     * @author HeHaoming
     * @version 1.0
     */

    private class NodeAndNeighbour {
        //顶点
        private Character vertex;
        //顶点对应的邻接点中未访问的邻接点
        LinkedList<Vertex> neighbourList;
        //顶点的访问状态
        boolean visited;

        /**
         * 构造方法
         *
         * @param vertex        顶点
         * @param neighbourList 顶点对应邻接点
         */

        NodeAndNeighbour(Character vertex, LinkedList<Vertex> neighbourList) {
            this.vertex = vertex;
            this.neighbourList = neighbourList;
            //初始化时访问状态置为false
            this.visited = false;
        }
    }

    /**
     * 获取小于固定深度的所有从起点到终点的路径，采用图的深度优先遍历
     *
     * @param depth 深度
     * @param depth 起点
     * @param end   终点
     * @return 路径列表
     */

    public List<String> DFSByCertainDepth(int depth, Character start, Character end) {
        //存放当前访问路径上的所有结点
        Stack<NodeAndNeighbour> stack = new Stack<NodeAndNeighbour>();
        //存放所有满足条件的路径
        List<String> track = new ArrayList<String>();
        //stack top元素
        NodeAndNeighbour top_node;
        //下一个访问结点
        NodeAndNeighbour next_node;
        //第一个入栈的起点，每个入栈的结点都维护一个自己未访问邻接点的列表（考虑到环中结点重复访问），以及一个访问位
        NodeAndNeighbour startNodeAndNeighbour = new NodeAndNeighbour(start, new LinkedList<Vertex>(this.vertices.get(start)));
        stack.push(startNodeAndNeighbour);
        //栈不为空
        while (!stack.isEmpty()) {
            //栈深度超过了限制深度弹出栈顶减少深度，即当前栈顶元素不满足条件
            if (stack.size() > depth + 1) {
                stack.pop();
                continue;
            }
            //栈顶元素
            top_node = stack.peek();
            //栈顶元素为终点且此路径未记录，则记录
            if (top_node.vertex == end && !top_node.visited) {
                StringBuilder sb = new StringBuilder();
                for (NodeAndNeighbour node : stack) {
                    sb.append(node.vertex);
                }
                track.add(sb.toString());
                //将此路径设置为以记录
                top_node.visited = true;
            } else {
                //访问top_node下一个邻接点
                next_node = getNextNode(top_node);
                //讲下一个结点入栈
                if (next_node != null) {
                    stack.push(next_node);
                } else {
                    //不存在临接点，将stack top元素退出
                    stack.pop();
                }
            }
        }
        return track;
    }

    /**
     * 获取顶点的第一个未访问邻接点，并删除此邻接点
     *
     * @param top_node 顶点
     * @return 第一个邻接点，不存在时返回null
     */
    private NodeAndNeighbour getNextNode(NodeAndNeighbour top_node) {
        //邻接点链表不为空
        if (!top_node.neighbourList.isEmpty()) {
            LinkedList<Vertex> neighbourList = top_node.neighbourList;
            //获取并删除第一个邻接点
            Vertex vertex = neighbourList.removeFirst();
            return new NodeAndNeighbour(vertex.getId(), new LinkedList<Vertex>(this.vertices.get(vertex.getId())));
        }
        //不存在邻接点返回null
        return null;
    }

    public Map<Character, List<Vertex>> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertices=" + vertices +
                '}';
    }
}
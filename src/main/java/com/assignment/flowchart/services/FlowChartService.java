package com.assignment.flowchart.services;

import com.assignment.flowchart.dto.FlowChartRequestDTO;
import com.assignment.flowchart.models.Edge;
import com.assignment.flowchart.models.FlowChart;
import com.assignment.flowchart.models.Node;
import com.assignment.flowchart.repository.IEdgeRepository;
import com.assignment.flowchart.repository.IFlowChartRepository;
import com.assignment.flowchart.repository.INodeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FlowChartService {

    private final Logger log = LogManager.getLogger(FlowChartService.class);

    private final IFlowChartRepository flowChartRepository;
    private final NodeService nodeService;
    private final EdgeService edgeService;

    public FlowChartService(IFlowChartRepository flowChartRepository, NodeService nodeService, EdgeService edgeService) {
        this.flowChartRepository = flowChartRepository;
        this.nodeService = nodeService;
        this.edgeService = edgeService;
    }

    public FlowChart createFlowChart(FlowChartRequestDTO dto) {

        Set<Edge> edges = new HashSet<>();
        Map<String, Node> nodeCache = new HashMap<>();

        for (List<String> edge : dto.getEdge()) {
            String from = edge.get(0);
            String to = edge.get(1);
            String edgeName = edge.get(2);


            Node aEnd = nodeCache.computeIfAbsent(from, nodeService::getOrCreateNode);
            Node zEnd = nodeCache.computeIfAbsent(to, nodeService::getOrCreateNode);

            Edge edge1 = edgeService.createEdge(edgeName,aEnd, zEnd);
            edges.add(edge1);
        }

        FlowChart flowChart = new FlowChart();
        flowChart.setTitle(dto.getTitle());
        edges.forEach(edge -> edge.setFlowchart(flowChart));
        flowChart.setEdges(edges);
        return flowChartRepository.save(flowChart);
    }



    public FlowChart getFlowChart(String name) {
        return flowChartRepository.getFlowChartByTitle(name);
    }

    public FlowChart getFlowChartById(int id) {
        return flowChartRepository.getFlowChartById(id);
    }

    public FlowChart updateFlowChart(int id,FlowChartRequestDTO dto) {
        FlowChart flowChart = flowChartRepository.getFlowChartById(id);
        if (flowChart == null) {
            log.error("FlowChart not found");
            throw new RuntimeException("FlowChart not found");
        }

        if (dto.getTitle() != null && !Objects.equals(dto.getTitle(), "") && !dto.getTitle().equals(flowChart.getTitle())) {
            flowChart.setTitle(dto.getTitle());
        }


        Map<String, Edge> existingEdgesMap = flowChart.getEdges().stream()
                .collect(Collectors.toMap(
                        edge -> edge.getaEnd().getName() + "->" + edge.getzEnd().getName(),
                        edge -> edge
                ));


        for (List<String> edge : dto.getEdge()) {
            String from = edge.get(0);
            String to = edge.get(1);
            String edgeName = edge.get(2);
            String edgeKey = from + "->" + to;
            if (!existingEdgesMap.containsKey(edgeKey)) {
                Edge nEdge = edgeService.createEdge(edgeName,nodeService.getOrCreateNode(from), nodeService.getOrCreateNode(to));
                flowChart.getEdges().add(nEdge);
                log.info("Added edge " + edgeKey + " to flow chart " + flowChart.getTitle());
            }else {
                existingEdgesMap.remove(edgeKey);
            }
        }

        for (Map.Entry<String,Edge> entry :existingEdgesMap.entrySet()) {
            Edge edge = entry.getValue();
            flowChart.getEdges().remove(edge);
            edgeService.deleteEdge(edge);
        }

        return flowChartRepository.save(flowChart);

    }
}

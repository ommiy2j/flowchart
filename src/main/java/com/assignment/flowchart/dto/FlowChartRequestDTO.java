package com.assignment.flowchart.dto;

import java.util.List;

public class FlowChartRequestDTO {
    String title;
    List<List<String>> edge;

    public FlowChartRequestDTO(String title, List<List<String>> edge) {
        this.title = title;
        this.edge = edge;
    }

    public String getTitle() {
        return title;
    }

    public FlowChartRequestDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<List<String>> getEdge() {
        return edge;
    }

    public FlowChartRequestDTO setEdge(List<List<String>> edge) {
        this.edge = edge;
        return this;
    }
}

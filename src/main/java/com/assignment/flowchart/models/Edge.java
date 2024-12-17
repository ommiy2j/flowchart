package com.assignment.flowchart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Edge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String label;

    @ManyToOne
    @JoinColumn(name = "a_end_node_id", nullable = false)
    @JsonIgnore
    private Node aEnd;

    @ManyToOne
    @JoinColumn(name = "z_end_node_id", nullable = false)
    @JsonIgnore
    private Node zEnd;

    @ManyToOne
    @JoinColumn(name = "flowchart_id")
    @JsonIgnore
    private FlowChart flowchart;


    public FlowChart getFlowchart() {
        return flowchart;
    }

    public Edge setFlowchart(FlowChart flowchart) {
        this.flowchart = flowchart;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public Edge setLabel(String label) {
        this.label = label;
        return this;
    }

    public Node getaEnd() {
        return aEnd;
    }

    public Edge setaEnd(Node aEnd) {
        this.aEnd = aEnd;
        return this;
    }

    public Node getzEnd() {
        return zEnd;
    }

    public Edge setzEnd(Node zEnd) {
        this.zEnd = zEnd;
        return this;
    }

    public long getId() {
        return id;
    }

    public Edge setId(long id) {
        this.id = id;
        return this;
    }
}

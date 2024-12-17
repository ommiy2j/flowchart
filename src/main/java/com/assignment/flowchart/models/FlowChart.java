package com.assignment.flowchart.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flow_chart")
public class FlowChart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "flowchart_id_sequence", sequenceName = "flow_chart_id",allocationSize = 50,initialValue = 1)
    private int id;

    @Column(name = "flow_chart_name")
    private String title;

    @OneToMany(mappedBy = "flowchart",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Edge> edges = new HashSet<>();

    public int getId() {
        return id;
    }

    public FlowChart setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FlowChart setTitle(String title) {
        this.title = title;
        return this;
    }



    public Set<Edge> getEdges() {
        return edges;
    }

    public FlowChart setEdges(Set<Edge> edges) {
        this.edges = edges;
        return this;
    }
}

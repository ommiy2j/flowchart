package com.assignment.flowchart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nodeName", nullable=false)
    private String name;

    @OneToMany(mappedBy = "zEnd",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Edge> outGoingEdges = new HashSet<Edge>();

    @OneToMany(mappedBy = "aEnd",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Edge> incomingEdges = new HashSet<Edge>();


    public long getId() {
        return id;
    }

    public Node setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Edge> getOutGoingEdges() {
        return outGoingEdges;
    }

    public Node setOutGoingEdges(Set<Edge> outGoingEdges) {
        this.outGoingEdges = outGoingEdges;
        return this;
    }

    public Set<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public Node setIncomingEdges(Set<Edge> incomingEdges) {
        this.incomingEdges = incomingEdges;
        return this;
    }
}

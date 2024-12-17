package com.assignment.flowchart.services;

import com.assignment.flowchart.models.Edge;
import com.assignment.flowchart.models.Node;
import com.assignment.flowchart.repository.IEdgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeService {

    private final IEdgeRepository edgeRepository;

    @Autowired
    public EdgeService(IEdgeRepository edgeRepository) {
        this.edgeRepository = edgeRepository;
    }

    public Edge createEdge(String name,Node aEnd,Node zEnd) {
        Edge edge = new Edge();
        edge.setaEnd(aEnd);
        edge.setzEnd(zEnd);
        edge.setLabel(name);
        return edgeRepository.save(edge);
    }

    public void deleteEdge(Edge edge) {
        edgeRepository.delete(edge);
    }
}

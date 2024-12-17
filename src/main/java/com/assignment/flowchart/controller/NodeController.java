package com.assignment.flowchart.controller;


import com.assignment.flowchart.models.Edge;
import com.assignment.flowchart.services.NodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/api/node")
@RestController
public class NodeController {

    private final Logger log = LogManager.getLogger(NodeController.class);

    private final NodeService nodeService;

    @Autowired
    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/outgoing")
    public ResponseEntity<?> getAllOutGoingNodes(@RequestParam int id) {
        Set<Edge> allOutgoingEdges = nodeService.getAllOutgoingEdges(id);
        return ResponseEntity.ok(allOutgoingEdges);
    }


}

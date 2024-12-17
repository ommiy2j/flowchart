package com.assignment.flowchart.repository;

import com.assignment.flowchart.models.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INodeRepository extends JpaRepository<Node, Integer> {
    Node findByName(String name);

}

package com.assignment.flowchart.repository;

import com.assignment.flowchart.models.FlowChart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlowChartRepository extends JpaRepository<FlowChart, Long> {

    FlowChart getFlowChartByTitle(String s);

    FlowChart getFlowChartById(int id);
}

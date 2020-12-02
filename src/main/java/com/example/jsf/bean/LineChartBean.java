package com.example.jsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.Valid;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.jsf.service.DataService;

@Component
@ManagedBean
@ViewScoped
public class LineChartBean {
  @Autowired
  private DataService dataService;
  private LineChartModel lineModel;
  @Valid
  private User user=new User();
  	
  public void execute() {
	  
  }
  
  public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

@PostConstruct
  public void init() {
      lineModel = new LineChartModel();
      LineChartSeries s = new LineChartSeries();
      s.setLabel("Population");

      dataService.getLineChartData().forEach(s::set);

      lineModel.addSeries(s);
      lineModel.setLegendPosition("e");
      Axis y = lineModel.getAxis(AxisType.Y);
      y.setMin(0.5);
      y.setMax(700);
      y.setLabel("Millions");

      Axis x = lineModel.getAxis(AxisType.X);
      x.setMin(0);
      x.setMax(7);
      x.setTickInterval("1");
      x.setLabel("Number of Years");

  }

  public LineChartModel getLineModel() {
      return lineModel;
  }
}
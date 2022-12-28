package com.softgraf.farmacia.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named("graficoMB")
@ViewScoped //javax.faces.view.ViewScoped
public class GraficoMB implements Serializable{


private static final long serialVersionUID = 3874620696438043988L;
	
private LineChartModel lineModel;
	
	public GraficoMB() {
		lineModel = new LineChartModel();
		lineModel.setTitle("Vendas");
		lineModel.setAnimate(true);
		
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setMax(1000000000);
		yAxis.setMin(-5);
		
		ChartSeries series = new LineChartSeries();
		series.set(2018,700000000);
		series.set(2019, 820000000);
		series.set(2020, 890000000);
		series.set(2021, 930000000);
		lineModel.addSeries(series);
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}
	
	
	
}

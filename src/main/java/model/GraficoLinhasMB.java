package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class GraficoLinhasMB  implements Serializable{

	private static final long serialVersionUID = -2181338738232958953L;
	
	private LineChartModel lineModel;
	
	public GraficoLinhasMB() {
		lineModel = new LineChartModel();
		lineModel.setTitle("Crescimento do PIB");
		lineModel.setAnimate(true);
		
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setMax(10);
		yAxis.setMin(-5);
		
		ChartSeries series = new LineChartSeries();
		series.set(2009, -0.13);
		series.set(2010, 7.53);
		series.set(2011, 3.97);
		series.set(2012, 1.92);
		series.set(2013, 3.0);
		series.set(2014, 0.5);
		series.set(2015, -3.55);
		series.set(2016, 3.31);
		series.set(2017, 1.06);
		series.set(2018, 1.12);
		series.set(2019, 1.20);
		series.set(2020, 4.6);
		series.set(2021, 4.6);
		lineModel.addSeries(series);
	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}
	
	
	
}

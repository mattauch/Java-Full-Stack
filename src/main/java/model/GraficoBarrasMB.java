package model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

//sempre que utiliza Bean implementa-se a interface Serializable -> marcação (no geral para o processamento ser bit a bit) - CPF do Objeto
@ManagedBean
public class GraficoBarrasMB implements Serializable {

	private static final long serialVersionUID = 947560811372428484L;

	private BarChartModel barModel;

	public GraficoBarrasMB() {
		barModel = new BarChartModel();
		barModel.setTitle("Crescimento do PIB");
		barModel.setAnimate(true);

		Axis eixoY = barModel.getAxis(AxisType.Y);
		eixoY.setMin(-5);
		eixoY.setMax(10);
		
		ChartSeries series = new ChartSeries();
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
		barModel.addSeries(series);

	}
	
	public BarChartModel getBarModel() {
		return barModel;
	}
	
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

}

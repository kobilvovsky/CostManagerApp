package il.ac.hit.java.costmanagerapp.view;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.util.HashMap;
import java.util.Map;

public class PieChart extends ChartPanel {
    public PieChart( String title, HashMap<String,Double> data) {
        super(createChart(createDataset(data)));
    }

    private static PieDataset createDataset(HashMap <String,Double> data) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        for (Map.Entry<String, Double> hashElement : data.entrySet()) {
            dataset.setValue(hashElement.getKey(), hashElement.getValue());
        }

        return dataset;
    }

    private static JFreeChart createChart( PieDataset dataset ) {
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} = {1}");
        JFreeChart chart = ChartFactory.createPieChart(
                "Expenses",   // chart title
                dataset,          // data
                true,
                true,
                false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelGenerator(labelGenerator);
        return chart;
    }


}
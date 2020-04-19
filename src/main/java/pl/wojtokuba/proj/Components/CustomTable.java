package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.table.DefaultTableRenderer;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.gui2.table.TableCellBorderStyle;

public class CustomTable<V> extends Table<V> {

    public CustomTable(String ...columnLables){
        super(columnLables);

        DefaultTableRenderer<V> renderer = (DefaultTableRenderer<V>) getRenderer();
        renderer.setCellHorizontalBorderStyle(TableCellBorderStyle.DoubleLine);
        renderer.setViewTopRow(5);
        renderer.setHeaderVerticalBorderStyle(TableCellBorderStyle.DoubleLine);
        setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                true,
                false,
                10,
                1)
        );
    }
}

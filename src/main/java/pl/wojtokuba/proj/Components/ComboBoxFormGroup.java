package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.gui2.*;

import java.util.Collection;

public class ComboBoxFormGroup<T> extends Panel {
    ComboBox<T> comboBox = new ComboBox<T>();
    Label label = new Label("");
    public ComboBoxFormGroup(String label, int labelSize, int comboBoxSize){
        this.label.setText(label);
        this.label.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.END,
                GridLayout.Alignment.FILL,
                true,
                false,
                labelSize,
                1
        ));
        this.comboBox.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                true,
                false,
                comboBoxSize,
                1));

        setLayoutManager(new GridLayout(10).setBottomMarginSize(1));
        addComponent(this.label);
        addComponent(this.comboBox);
        setLayoutData(
                GridLayout.createLayoutData(
                        GridLayout.Alignment.FILL,
                        GridLayout.Alignment.END,
                        true,
                        false,
                        10,
                        1
                )
        );
    }

    public T getSelected(){
        return this.comboBox.getSelectedItem();
    }

    public ComboBoxFormGroup<T> setValues(Collection<T> items){
        for (T item : items) {
            this.comboBox.addItem(item);
        }
         return this;
    }
}

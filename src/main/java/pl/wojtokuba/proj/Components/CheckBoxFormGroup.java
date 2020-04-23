package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.gui2.*;

public class CheckBoxFormGroup extends Panel {
    CheckBox checkBox = new CheckBox();
    Label label = new Label("");
    public CheckBoxFormGroup(String label, int labelSize, int textBoxSize){
        this.label.setText(label);
        this.label.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.END,
                GridLayout.Alignment.FILL,
                true,
                false,
                labelSize,
                1
        ));
        this.checkBox.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                true,
                false,
                textBoxSize,
                1));

        setLayoutManager(new GridLayout(10).setBottomMarginSize(1));
        addComponent(this.label);
        addComponent(this.checkBox);
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

    public boolean getIsChecked() {
        return this.checkBox.isChecked();
    }

    public CheckBoxFormGroup setValue(boolean value){
         this.checkBox.setChecked(value);
         return this;
    }
}

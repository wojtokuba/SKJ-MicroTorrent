package pl.wojtokuba.proj.Components;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;

public class TextBoxFormGroup extends Panel {
    TextBox textBox = new TextBox();
    Label label = new Label("");
    public TextBoxFormGroup(String label, int labelSize, int textBoxSize){
        this.label.setText(label);
        this.label.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.END,
                GridLayout.Alignment.FILL,
                true,
                false,
                labelSize,
                1
        ));
        this.textBox.setLayoutData(GridLayout.createLayoutData(
                GridLayout.Alignment.FILL,
                GridLayout.Alignment.FILL,
                true,
                false,
                textBoxSize,
                1));

        setLayoutManager(new GridLayout(10).setBottomMarginSize(1));
        addComponent(this.label);
        addComponent(this.textBox);
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

    public String getValue(){
        return this.textBox.getText();
    }

    public TextBoxFormGroup setValue(String value){
         this.textBox.setText(value);
         return this;
    }
}

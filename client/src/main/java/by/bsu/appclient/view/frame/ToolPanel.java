package by.bsu.appclient.view.frame;

import javax.swing.*;
import java.awt.*;

/**
 * Created by User on 21.03.2017.
 */
public abstract class ToolPanel<E extends Enum<E>> extends JPanel {

    private String enumName;

    public ToolPanel(String enumName) {
        super(new FlowLayout());
        this.enumName = enumName;
        fillToolPanel();
    }

    private void fillToolPanel() {
        try{
            Class toolEnumClass = Class.forName(enumName);
            E[] tools = (E[])toolEnumClass.getMethod("values").invoke(null);
            for(E tool : tools){
                JButton toolButton = createToolButtonAndInitListener(tool);
                add(toolButton);
            }
        }
        catch (Exception e){
            System.out.println("error");
        }
    }

    private JButton createToolButtonAndInitListener(final E tool) {
        JButton toolButton = new JButton(tool.name());
        initToolButtonListener(tool, toolButton);
        return toolButton;
    }

    protected abstract void initToolButtonListener(final E tool, JButton toolButton);

}
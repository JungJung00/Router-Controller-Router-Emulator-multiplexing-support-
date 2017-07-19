package routerControllerGUI;

import common.ResultSocketStructure;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import router.*;
import routerControlManager.RouterManager;
import routerController.RouterController;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import static common.Now.getTime;

/**
 * Created by Jeong Taegyun on 2017-04-19.
 */

public class RouterControllerGUI {
    private JPanel panelMain;
    private JList connectedDeviceList;
    private JScrollPane connectedDeviceListPane;
    private JPanel configurationButtonPane;
    private JButton power;
    private JButton SSIDPasswordButton;
    private JButton DHCPButton;
    private JTextArea responseMessageArea;
    private JButton refreshButton;
    private JPanel portforwardingField;
    private JPanel DHCPField;
    private JPanel SSIDPasswordField;
    private JTextField ruleNameText;
    private JFormattedTextField innerIPText;
    private JTextField innerPortText;
    private JTextField outerPortText;
    private JFormattedTextField minDHCPAddressRangeTextField;
    private JFormattedTextField maxDHCPAddressRangeTextField;
    private JTextField passwordTextField;
    private JTextField SSIDTextField;
    private JButton DHCPSetButton;
    private JButton SSIDPasswordSetButton;
    private JButton portForwardingButton;
    private JButton portForwardingSetButton;
    private JTextField IPAllocationTime;
    private JPanel routerListPane;
    private JList connectedRouterList;
    private JPanel connectedRouterListPane;
    private JPanel disconnectedRouterListPane;
    private JButton connectButton;
    private JButton reconnectButton;
    private JButton disconnectButton;
    private JPanel connectedRouterButtonPane;
    private JList controllableRouterList;
    private JButton removeButton;

    private static RouterController routerController;

    private ControllableRouterList controllableRouter = null;
    private static HashMap<Integer, RouterManager> connectedRouter;
    private static RouterManager selectedRouter;
    private static int selectedRouteridential;

    public RouterControllerGUI(){
        connectedRouter = new HashMap<>();

        DefaultListModel dlm = (DefaultListModel) connectedDeviceList.getModel();
        controllableRouterList.setModel(new DefaultListModel());
        DefaultListModel controllableRouterListModel = (DefaultListModel) controllableRouterList.getModel();
        DefaultListModel connectedRouterListModel = (DefaultListModel) connectedRouterList.getModel();

        // Set usable router list
        controllableRouter = new ControllableRouterList();
        ArrayList<Integer> controllableRouters = controllableRouter.getControllableRouterLists();
        for (int i = 0; i < 19; i++){
            controllableRouterListModel.addElement(controllableRouters.get(i));
        }

        // ON/OFF router power
        power.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!selectedRouter.isConnected()){
                    responseMessageArea.append(getTime() + " " + ((JSONObject)routerController.turnOnPower(selectedRouter).get("header")).get("result") + "\n");

                    // ResultSocketStructure result = routerController.getConnectedDevices();
                    JSONObject result = routerController.getConnectedDevices(selectedRouter);

                    // ArrayList<ConnectedDeviceInfo> connectedDevices = result.getConnectedDevices();
                    JSONArray connectedDevices = (JSONArray)((JSONObject)result.get("body")).get("subValues");
                    responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");

                    dlm.clear();

                    for(int i = 0; i < connectedDevices.size(); i++){
                        JSONObject device = (JSONObject)connectedDevices.get(i);
                        dlm.addElement(device.get("ip") + " / " + device.get("mac"));
                    }
                }
                else{
                    // responseMessageArea.append(getTime() + " " + ((JSONObject)routerController.turnOffPower().get("header")).get("result") + "\n");
                }
            }
        });

        // Refresh connected devices list
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ResultSocketStructure result = routerController.getConnectedDevices();
                JSONObject result = routerController.getConnectedDevices(selectedRouter);

                // ArrayList<ConnectedDeviceInfo> connectedDevices = result.getConnectedDevices();
                JSONArray connectedDevices = (JSONArray)((JSONObject)result.get("body")).get("subValues");
                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");

                dlm.clear();

                for(int i = 0; i < connectedDevices.size(); i++){
                    JSONObject device = (JSONObject)connectedDevices.get(i);
                    dlm.addElement(device.get("ip") + " / " + device.get("mac"));
                }
            }
        });

        // Get/Set DHCP configurations
        DHCPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ResultSocketStructure result = routerController.getDHCPConfiguration();
                JSONObject result = routerController.getDHCPConfiguration(selectedRouter);

                // DHCPInfo dhcpConfiguration = result.getDhcpConfigurations();
                JSONObject dhcpConfiguration = (JSONObject)((JSONArray)((JSONObject)result.get("body")).get("subValues")).get(0);

                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");

                dlm.clear();
                dlm.addElement("DHCP IP Range : " + dhcpConfiguration.get("start") + " ~ " + dhcpConfiguration.get("end") + " | Allocation Time : " + dhcpConfiguration.get("rental"));
            }
        });
        DHCPSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ResultSocketStructure result = routerController.setDHCPConfiguration(minDHCPAddressRangeTextField.getText(), maxDHCPAddressRangeTextField.getText(), IPAllocationTime.getText());
                JSONObject result = routerController.setDHCPConfiguration(selectedRouter, minDHCPAddressRangeTextField.getText(), maxDHCPAddressRangeTextField.getText(), Integer.parseInt(IPAllocationTime.getText()));

                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");
            }
        });

        // Get/Set portforwarding configuration
        portForwardingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ResultSocketStructure result = routerController.getPortForwardingConfiguration();
                JSONObject result = routerController.getPortForwardingConfiguration(selectedRouter);

                // ArrayList<PortForwardingInfo> portForwardingConfigurations = result.getPortForwardingConfigurations();
                JSONArray portForwardingConfigurations = (JSONArray)((JSONObject)result.get("body")).get("subValues");

                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");

                dlm.clear();

                for(int i = 0; i < portForwardingConfigurations.size(); i++){
                    // PortForwardingInfo pfi = portForwardingConfigurations.get(i);
                    JSONObject pfi = (JSONObject)portForwardingConfigurations.get(i);
                    dlm.addElement(pfi.get("name") + " : " + "Inner IP " + pfi.get("IP") + " | Outer Port "
                                + pfi.get("externalPort") + " | Inner Port " + pfi.get("internalPort"));
                }
            }
        });
        portForwardingSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ResultSocketStructure result = routerController.setPortForwardingConfiguration(
//                        ruleNameText.getText(),
//                        innerIPText.getText(),
//                        outerPortText.getText(),
//                        innerPortText.getText()
//                );
                JSONObject result = routerController.setPortForwardingConfiguration(
                        selectedRouter,
                        ruleNameText.getText(),
                        innerIPText.getText(),
                        Integer.parseInt(outerPortText.getText()),
                        Integer.parseInt(innerPortText.getText())
                );

                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");
            }
        });

        /* Get/Set Security configuration */
        SSIDPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ResultSocketStructure result = routerController.getSecurityConfiguration();
                JSONObject result = routerController.getSecurityConfiguration(selectedRouter);

                // SecurityConfigurationInfo securityConfiguration = result.getSecurityConfigurationInfo();
                JSONArray securityConfiguration = (JSONArray)((JSONObject)result.get("body")).get("subValues");

                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");

                dlm.clear();
                dlm.addElement("SSID : " + securityConfiguration.get(0) + " | Password : " + securityConfiguration.get(1));
            }
        });
        SSIDPasswordSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ResultSocketStructure result = routerController.setSecurityConfiguration(
//                        SSIDTextField.getText(),
//                        passwordTextField.getText()
//                );
                JSONObject result = routerController.setSecurityConfiguration(
                        selectedRouter,
                        SSIDTextField.getText(),
                        passwordTextField.getText()
                );
                responseMessageArea.append(getTime() + " " + ((JSONObject)result.get("header")).get("result") + "\n");
            }
        });

        /* Router List Buttons Listener */
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected router identification number...
                selectedRouteridential = Integer.parseInt(controllableRouterList.getSelectedValue().toString());
                // ... and remove
                controllableRouterListModel.remove(controllableRouterList.getSelectedIndex());

                // Ultimately move element from controllableRouterList to connectedRouterList
                connectedRouterListModel.addElement(selectedRouteridential);

                /* TODO : Get IP and port from server */
                selectedRouter = new RouterManager(/* temporary data */"192.168.0.7", 5500);

                // Save router information to keep the connection
                connectedRouter.put(selectedRouteridential, selectedRouter);

                responseMessageArea.append(getTime() + " Now control the router, " + selectedRouteridential + "("
                        + selectedRouter.getIP() + " : "
                        + selectedRouter.getPORT() + ")\n");
            }
        });
        reconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If there is no selected list item, handle operation with selectedRouter which is allocated in connectButton Listener
                if (connectedRouterList.getSelectedValue() != null){
                    selectedRouteridential = Integer.parseInt(connectedRouterList.getSelectedValue().toString());
                    selectedRouter = connectedRouter.get(selectedRouteridential);
                }

                if(!(selectedRouter.connectRouter())){
                    // If fail to connect router, show failure message
                    responseMessageArea.append(getTime() + " Fail to connect to " + selectedRouteridential + "("
                            + selectedRouter.getIP() + " : "
                            + selectedRouter.getPORT() + ").\nPlease try again or check the network is ok.\n");
                } else{ // or success message
                    responseMessageArea.append(getTime() + " Succeed to connect to " + selectedRouteridential + "("
                            + selectedRouter.getIP() + " : "
                            + selectedRouter.getPORT() + ")\n");
                }
            }
        });
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If there is no selected list item, handle operation with selectedRouter which is allocated in connectButton Listener
                if (connectedRouterList.getSelectedValue() != null){
                    selectedRouteridential = Integer.parseInt(connectedRouterList.getSelectedValue().toString());
                    selectedRouter = connectedRouter.get(selectedRouteridential);
                }

                selectedRouter.disconnectRouter();

                responseMessageArea.append(getTime() + " Succeed to disconnect to " + selectedRouteridential + "("
                        + selectedRouter.getIP() + " : "
                        + selectedRouter.getPORT() + ")\n");
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectedRouterList.getSelectedValue() != null){
                    selectedRouteridential = Integer.parseInt(connectedRouterList.getSelectedValue().toString());
                    selectedRouter = connectedRouter.get(selectedRouteridential);

                    // Router connection must be disconnected before remove it from connected router list
                    if (!selectedRouter.isConnected()){
                        // subtract 30101 from selectedRouteridential to get a correct index
                        // amount of 30100 is for to extract necessary identifiable number
                        // amount of 1 is for to fit index of array that starts from 0
                        controllableRouterListModel.add(selectedRouteridential - 30101, selectedRouteridential);
                        connectedRouterListModel.remove(connectedRouterList.getSelectedIndex());

                        responseMessageArea.append(getTime() + " Now not control the router, " + selectedRouteridential + "("
                                + selectedRouter.getIP() + " : "
                                + selectedRouter.getPORT() + ")\n");

                        selectedRouteridential = -1;
                        selectedRouter = null;
                    }
                    else{
                        responseMessageArea.append(getTime() + " Please disconnect to router, " + selectedRouteridential + "("
                                + selectedRouter.getIP() + " : "
                                + selectedRouter.getPORT() + ") before remove it\n");
                    }
                }
            }
        });

        routerController = new RouterController();
    }

    public static void main(String args[]){
        JFrame frame = new JFrame("RouterControllerGUI");
        RouterControllerGUI routerControllerGUI = new RouterControllerGUI();
        frame.setContentPane(routerControllerGUI.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try{
            MaskFormatter mf = new MaskFormatter("###.###.###.###");
            DefaultFormatterFactory dff = new DefaultFormatterFactory(mf);
            routerControllerGUI.minDHCPAddressRangeTextField.setFormatterFactory(dff);
            routerControllerGUI.maxDHCPAddressRangeTextField.setFormatterFactory(dff);
            routerControllerGUI.innerIPText.setFormatterFactory(dff);
        } catch (ParseException e){
            e.printStackTrace();
        }

        frame.pack();
        frame.setVisible(true);

    }
}

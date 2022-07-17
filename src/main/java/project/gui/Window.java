/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.Scalars;
import graphql.introspection.IntrospectionQuery;
import graphql.introspection.IntrospectionResultToSchema;
import graphql.language.Document;
import graphql.parser.Parser;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.SchemaPrinter;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.HttpHeaders.ACCEPT_ENCODING;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import project.parser.service.ParseService;
import project.web.RequestDefinition;
import project.web.service.GetSchemaService;

/**
 *
 * @author sparf
 */
public class Window extends javax.swing.JFrame {

    GraphQLSchema schema;
    private GetSchemaService getSchemaService = new GetSchemaService();
    private ParseService parseService = new ParseService();

    /**
     * Creates new form Window
     */
    public Window() {
        initComponents();
        init();
    }

    private void init() {
        ((PlainDocument) requestsTA.getDocument()).setDocumentFilter(new ChangeTabToSpacesFilter(4));
        requestsTA.setLayout(new BorderLayout());

        addRequestJLListener();
    }

    private void addRequestJLListener() {
        requestListJL.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    String text = parseService.getAllParsed(requestListJL.getSelectedValuesList());
                    requestsTA.setText(text);
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textD = new javax.swing.JDialog();
        exSP = new javax.swing.JScrollPane();
        exTA = new javax.swing.JTextArea();
        getShemaB = new javax.swing.JButton();
        urlL = new java.awt.Label();
        authorizationL = new java.awt.Label();
        requestsSP = new javax.swing.JScrollPane();
        requestsTA = new javax.swing.JTextArea();
        urlJTF = new javax.swing.JTextField();
        authorizationJTF = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        requestListJL = new javax.swing.JList<>();
        queryJCB = new javax.swing.JCheckBox();
        mutationJCB = new javax.swing.JCheckBox();
        exportJB = new javax.swing.JButton();

        exTA.setEditable(false);
        exTA.setColumns(20);
        exTA.setRows(5);
        exSP.setViewportView(exTA);

        javax.swing.GroupLayout textDLayout = new javax.swing.GroupLayout(textD.getContentPane());
        textD.getContentPane().setLayout(textDLayout);
        textDLayout.setHorizontalGroup(
            textDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exSP, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        textDLayout.setVerticalGroup(
            textDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exSP, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        getShemaB.setText("Get schema");
        getShemaB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getShemaBActionPerformed(evt);
            }
        });

        urlL.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        urlL.setText("Url:");

        authorizationL.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        authorizationL.setText("Authorization:");

        requestsTA.setEditable(false);
        requestsTA.setColumns(20);
        requestsTA.setRows(5);
        requestsSP.setViewportView(requestsTA);

        urlJTF.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        urlJTF.setToolTipText("");

        authorizationJTF.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        requestListJL.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(requestListJL);

        queryJCB.setSelected(true);
        queryJCB.setText("query");
        queryJCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                queryJCBActionPerformed(evt);
            }
        });

        mutationJCB.setText("mutation");
        mutationJCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mutationJCBActionPerformed(evt);
            }
        });

        exportJB.setText("Export");
        exportJB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportJBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(authorizationL, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(authorizationJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(urlL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(urlJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(getShemaB, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(queryJCB)
                        .addGap(18, 18, 18)
                        .addComponent(mutationJCB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exportJB)
                    .addComponent(requestsSP, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(urlL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(urlJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(authorizationL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(authorizationJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(getShemaB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(queryJCB)
                    .addComponent(mutationJCB)
                    .addComponent(exportJB))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(requestsSP, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getShemaBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getShemaBActionPerformed
        try {
            String url = urlJTF.getText();
            String authorization = authorizationJTF.getText();

            //Local testing
            if (false) {
                url = "http://localhost:8090/graphql";
                authorization = "dXNlcjpwYXNzd29yZA==";
            }

            schema = getSchemaService.getSchema(url, authorization);

            populateRequestListJTL();
        } catch (Exception ex) {
            showDialog(ex);
        }
    }//GEN-LAST:event_getShemaBActionPerformed

    private void showDialog(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        
        exTA.setText(sw.toString());
        textD.setSize(200, 200);
        textD.setLocationRelativeTo(null);
        textD.setVisible(true);
    }

    private void queryJCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_queryJCBActionPerformed
        populateRequestListJTL();
    }//GEN-LAST:event_queryJCBActionPerformed

    private void mutationJCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mutationJCBActionPerformed
        populateRequestListJTL();
    }//GEN-LAST:event_mutationJCBActionPerformed

    private void exportJBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportJBActionPerformed
        String fileName = "export.txt";
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(requestsTA.getText());
        } catch (IOException e) {
            exTA.setText(e.getMessage());
            textD.setVisible(true);
        }
    }//GEN-LAST:event_exportJBActionPerformed

    private void populateRequestListJTL() {
        if (schema != null) {
            List<RequestDefinition> result = new ArrayList<>();

            if (queryJCB.isSelected()) {
                result.addAll(getRequestDefinitions(true, schema.getQueryType().getFieldDefinitions()));
            }

            if (mutationJCB.isSelected()) {
                result.addAll(getRequestDefinitions(false, schema.getMutationType().getFieldDefinitions()));
            }

            requestListJL.setListData(result.toArray(new RequestDefinition[0]));
        }
    }

    private List<RequestDefinition> getRequestDefinitions(boolean query,
            List<GraphQLFieldDefinition> definitions) {

        return definitions.stream()
                .map(definition -> new RequestDefinition(query, definition))
                .collect(Collectors.toList());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClientProtocolException, IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Toolkit.getDefaultToolkit().getSystemEventQueue().push(new TCPopupEventQueue());

                Window window = null;
                try {
                    window = new Window();
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                } catch (Exception e) {
                    window.exTA.setText(e.getMessage());
                    window.textD.setVisible(true);
                }
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorizationJTF;
    private java.awt.Label authorizationL;
    private javax.swing.JScrollPane exSP;
    private javax.swing.JTextArea exTA;
    private javax.swing.JButton exportJB;
    private javax.swing.JButton getShemaB;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox mutationJCB;
    private javax.swing.JCheckBox queryJCB;
    private javax.swing.JList<RequestDefinition> requestListJL;
    private javax.swing.JScrollPane requestsSP;
    private javax.swing.JTextArea requestsTA;
    private javax.swing.JDialog textD;
    private javax.swing.JTextField urlJTF;
    private java.awt.Label urlL;
    // End of variables declaration//GEN-END:variables
}
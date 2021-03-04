// Caluculator kokugosogo

import java.io.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.collections.*;
import javafx.scene.input.*;

public class Calculator extends Application {
    private TextField tf;
    private Button[][] bt = new Button[4][5];
    private Boolean is_error = false;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        // コントロール(テキストフィールド)の作成
        tf = new TextField();
        tf.setEditable(false);
        tf.setMaxWidth(380);
        tf.setFont(Font.font("MonoSpace", 40));
        tf.setAlignment(Pos.CENTER_RIGHT);

        // コントロールボタンの追加
        String[][] bt_str = { { "CE", "C", "BS", "/" }, { "7", "8", "9", "*" }, { "4", "5", "6", "-" },
                { "1", "2", "3", "+" }, { "±", "0", ".", "=" } };
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                bt[i][j] = new Button(bt_str[j][i]);
                bt[i][j].setPrefWidth(80);
                bt[i][j].setPrefHeight(80);
                bt[i][j].setFont(Font.font("MonoSpace", 30));
                int idx_i = i;
                int idx_j = j;
                bt[i][j].setOnAction((ActionEvent) -> {
                    String in = bt[idx_i][idx_j].getText(); // ボタンから文字を取得
                    StringBuffer stb = new StringBuffer(tf.getText()); // TexttFieldから表示されている文字を取得
                    String str_stb = new String(stb); // StringBufferをStringに変換
                    String regex = "[+\\-]?[0-9]+.?[0-9]*[+\\-\\*/]{1}[0-9]+.?[0-9]*"; // 正解の文字列の正規表現
                    if (in == "CE" || in == "C") {
                        tf.clear();
                        stb.delete(0, stb.length());
                        str_stb = null;
                        is_error = false;
                    } else if (in == "BS") {
                        if (stb.length() != 0) {
                            stb.deleteCharAt(stb.length() - 1);
                            str_stb = new String(stb);
                            tf.setText(str_stb);
                        }
                    } else if (in == "±") {
                        if (stb.length() > 0) {
                            if (Character.isDigit(stb.charAt(0))) {
                                stb.insert(0, "-");
                                str_stb = new String(stb);
                                tf.setText(str_stb);
                            } else {
                                char stb_0 = stb.charAt(0);
                                if (stb_0 == '-') {
                                    stb.deleteCharAt(0);
                                    str_stb = new String(stb);
                                    tf.setText(str_stb);
                                }
                            }
                        }
                    } else if (in == "=") {
                        if (str_stb.matches(regex)) {
                            String regex2 = "(?=[+\\-\\*/])";
                            String[] operands = str_stb.toString().split(regex2);
                            BigDecimal op1 = new BigDecimal(operands[0]);
                            BigDecimal op2 = new BigDecimal(operands[1].substring(1));
                            System.out.println(String.valueOf(op2));
                            String symbol = operands[1].substring(0, 1);
                            if (symbol.equals("+")) {
                                BigDecimal result = op1.add(op2);
                                tf.setText(String.valueOf(result));
                            } else if (symbol.equals("-")) {
                                BigDecimal result = op1.subtract(op2);
                                tf.setText(String.valueOf(result));
                            } else if (symbol.equals("*")) {
                                BigDecimal result = op1.multiply(op2);
                                tf.setText(String.valueOf(result));
                            } else if (symbol.equals("/")) {
                                BigDecimal result = op1.divide(op2, 3, RoundingMode.HALF_UP);
                                tf.setText(String.valueOf(result));
                            } else {
                                tf.setText("Error");
                            }
                        } else {
                            tf.setText("Error");
                            is_error = true;
                        }
                    } else {
                        if (!is_error) {
                            stb.append(in);
                            str_stb = new String(stb);
                            tf.setText(str_stb);
                        }
                    }
                });
            }
        }

        // グリッドペインの作成とコントロールの追加
        GridPane gp = new GridPane();
        gp.setHgap(2);
        gp.setVgap(2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                gp.add(bt[i][j], i, j);
            }
        }
        gp.setAlignment(Pos.CENTER);

        // ボーダーペインの作成とコントロールの追加
        BorderPane bp = new BorderPane();
        bp.setTop(tf);
        bp.setAlignment(tf, Pos.CENTER);
        bp.setCenter(gp);

        Scene sc = new Scene(bp, 400, 600);
        stage.setScene(sc);
        stage.setTitle("電卓");
        stage.show();
    }
}
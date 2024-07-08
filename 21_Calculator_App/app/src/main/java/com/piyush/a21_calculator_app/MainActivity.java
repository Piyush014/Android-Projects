package com.piyush.a21_calculator_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigInteger;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView primaryTV, secondaryTV;
    Button acBtn, cBtn, brace1Btn, brace2Btn, sinBtn, cosBtn, tanBtn, logBtn, lnBtn, factBtn, squareBtn, sqRootBtn, invBtn, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint, btnMul, btnSub, btnAdd, btnEqual, btnDiv, btnPie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primaryTV = findViewById(R.id.idTVPrimary);
        secondaryTV = findViewById(R.id.idTVSecondary);
        acBtn = findViewById(R.id.idBtnAc);
        cBtn = findViewById(R.id.idBtnC);
        brace1Btn = findViewById(R.id.idBtnBrac1);
        brace2Btn = findViewById(R.id.idBtnBrac2);
        sinBtn = findViewById(R.id.idBtnSin);
        cosBtn = findViewById(R.id.idBtnCot);
        tanBtn = findViewById(R.id.idBtnTan);
        logBtn = findViewById(R.id.idBtnLog);
        lnBtn = findViewById(R.id.idBtnLn);
        factBtn = findViewById(R.id.idBtnfact);
        squareBtn = findViewById(R.id.idBtnSquare);
        sqRootBtn = findViewById(R.id.idBtnSqRoot);
        invBtn = findViewById(R.id.idBtnInv);
        btn0 = findViewById(R.id.idBtn0);
        btn1 = findViewById(R.id.idBtn1);
        btn2 = findViewById(R.id.idBtn2);
        btn3 = findViewById(R.id.idBtn3);
        btn4 = findViewById(R.id.idBtn4);
        btn5 = findViewById(R.id.idBtn5);
        btn6 = findViewById(R.id.idBtn6);
        btn7 = findViewById(R.id.idBtn7);
        btn8 = findViewById(R.id.idBtn8);
        btn9 = findViewById(R.id.idBtn9);
        btnPoint = findViewById(R.id.idBtnDot);
        btnMul = findViewById(R.id.idBtnMul);
        btnSub = findViewById(R.id.idBtnSub);
        btnAdd = findViewById(R.id.idBtnAdd);
        btnEqual = findViewById(R.id.idBtnEqual);
        btnDiv = findViewById(R.id.idBtnDiv);
        btnPie = findViewById(R.id.idBtnPie);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                primaryTV.setText(primaryTV.getText().toString() + b.getText().toString());
            }
        };

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btnPoint.setOnClickListener(listener);
        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
        brace1Btn.setOnClickListener(listener);
        brace2Btn.setOnClickListener(listener);
        sinBtn.setOnClickListener(listener);
        cosBtn.setOnClickListener(listener);
        tanBtn.setOnClickListener(listener);
        logBtn.setOnClickListener(listener);
        lnBtn.setOnClickListener(listener);
        squareBtn.setOnClickListener(listener);
        invBtn.setOnClickListener(listener);
        btnPie.setOnClickListener(listener);

        // Special buttons that need custom handling
        acBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryTV.setText("");
                secondaryTV.setText("");
            }
        });

        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = primaryTV.getText().toString();
                if (!val.isEmpty()) {
                    val = val.substring(0, val.length() - 1);
                    primaryTV.setText(val);
                }
            }
        });

        factBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = primaryTV.getText().toString();
                if (!val.isEmpty()) {
                    primaryTV.setText(val + "!");
                }
            }
        });

        sqRootBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryTV.setText("√(");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = primaryTV.getText().toString();
                String replacedStr = val.replace('×', '*').replace('÷', '/');
                try {
                    double result = eval(replacedStr);
                    DecimalFormat df = new DecimalFormat("#.####");
                    primaryTV.setText(df.format(result));
                } catch (Exception e) {
                    primaryTV.setText("Error");
                }
                secondaryTV.setText(val);
            }
        });
    }

    // Function to evaluate the mathematical expression
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)` | number
            //        | functionName factor | factor `^` factor | factor `!`

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else if (func.equals("log")) x = Math.log10(x);
                    else if (func.equals("ln")) x = Math.log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation
                if (eat('!')) x = factorial((int) x); // factorial

                return x;
            }

            double factorial(int n) {
                if (n == 0) return 1;
                if (n < 0) throw new IllegalArgumentException("Negative factorial not defined");
                BigInteger result = BigInteger.ONE;
                for (int i = 2; i <= n; i++) {
                    result = result.multiply(BigInteger.valueOf(i));
                }
                return result.doubleValue();
            }
        }.parse();
    }
}

package com.example.szachy.Dialogi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.szachy.R;

public class Promocja_pionka extends AppCompatDialogFragment
{
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    public Dialog onCreateDialog(Bundle zapis)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View widok = inflater.inflate(R.layout.promocja_pionek,null);
        radioGroup = widok.findViewById(R.id.grupa_promocji);

        builder.setView(widok)
                .setTitle("Pionek zyskał promocję.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        int selectedId = radioGroup.getCheckedRadioButtonId();

                        radioButton = widok.findViewById(selectedId);
                        Wynik_zwracania aktywnosc = (Wynik_zwracania) getActivity();
                        aktywnosc.aktywuj_promocje(radioButton.getText().toString());
                    }
                })
                ;
        return builder.create();
    }

    public interface Wynik_zwracania
    {
        void aktywuj_promocje(String promocja);
    }
}
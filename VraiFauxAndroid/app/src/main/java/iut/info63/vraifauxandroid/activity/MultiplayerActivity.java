package iut.info63.vraifauxandroid.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.Set;

import iut.info63.vraifauxandroid.R;

public class MultiplayerActivity extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        //mBluetoothAdapter.startDiscovery();
        //Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        //ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pairedDevices);
        //if (pairedDevices.size() > 0) {
        //    for (BluetoothDevice device : pairedDevices) {
        //        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
        //    }
        //}


        //REND LE TELEPHONE VISIBLE EN BLUETOOTH PENDANT 300 SECONDES
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }
}

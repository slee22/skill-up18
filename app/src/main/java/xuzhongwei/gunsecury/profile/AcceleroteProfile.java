package xuzhongwei.gunsecury.profile;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import java.util.List;

import xuzhongwei.gunsecury.common.GattInfo;
import xuzhongwei.gunsecury.service.BluetoothLeService;
import xuzhongwei.gunsecury.util.Adapter.Point3D;
import xuzhongwei.gunsecury.util.Adapter.sensor.Sensor;

public class AcceleroteProfile extends GenericBleProfile {
    public AcceleroteProfile(BluetoothLeService bluetoothLeService, BluetoothGattService bluetoothGattService,BluetoothDevice device) {
        super(bluetoothLeService, bluetoothGattService,device);

        List<BluetoothGattCharacteristic> charalist = bluetoothGattService.getCharacteristics();
        for(BluetoothGattCharacteristic c:charalist){

            if(c.getUuid().toString().equals(GattInfo.UUID_ACC_DATA.toString())){
                this.normalData = c;
            }

            if(c.getUuid().toString().equals(GattInfo.UUID_ACC_CONF.toString())){
                this.configData = c;
            }

            if(c.getUuid().toString().equals(GattInfo.UUID_ACC_PERI.toString())){
                this.periodData = c;
            }
        }
    }


    public void updateData(byte[] value){

        Point3D v = Sensor.ACCELEROMETER.convert(value);
        if(mOnDataChangedListener != null){
            mOnDataChangedListener.onDataChanged(String.format("X:%.2fG\n Y:%.2fG\n Z:%.2fG", v.x,v.y,v.z));
        }

    }
}

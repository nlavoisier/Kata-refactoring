package tddmicroexercises.tirepressuremonitoringsystem;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ben on 14-4-11.
 */
public class AlarmTest {
    @Test
    public void when_check_alarm_then_get_characterization() {
        Alarm alarm = new Alarm();
        alarm.check();
        assertThat(alarm.isAlarmOn()).isEqualTo(false);
    }

    @Test
    public void given_psi_pressure_value_is_less_than_17_when_check_alarm_then_alarm_is_on() {
        Sensor sensor = mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(16.0);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        assertThat(alarm.isAlarmOn()).isEqualTo(true);
    }

    @Test
    public void given_psi_pressure_value_is_greater_than_21_when_check_alarm_then_alarm_is_on() {
        Sensor sensor = mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(22.0);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        assertThat(alarm.isAlarmOn()).isEqualTo(true);
    }

    @Test
    public void given_psi_pressure_value_is_between_17_and_21_when_check_alarm_then_alarm_is_off() {
        Sensor sensor = mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(17.0).thenReturn(19.0).thenReturn(21.0);
        Alarm alarm = new Alarm(sensor);

        alarm.check();
        assertThat(alarm.isAlarmOn()).isEqualTo(false);

        alarm.check();
        assertThat(alarm.isAlarmOn()).isEqualTo(false);

        alarm.check();
        assertThat(alarm.isAlarmOn()).isEqualTo(false);
    }
}

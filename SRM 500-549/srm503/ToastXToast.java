package srm503;

import java.util.Arrays;

public class ToastXToast {
    public int bake(int[] undertoasted, int[] overtoasted) {
        Arrays.sort(undertoasted);
        Arrays.sort(overtoasted);
        if (overtoasted[0] <= undertoasted[0])
            return -1;
        if (overtoasted[overtoasted.length - 1] <= undertoasted[undertoasted.length - 1])
            return -1;
        for (int j = undertoasted.length - 1; j >= 0; j--)
            if (overtoasted[0] > undertoasted[j]) {
                if (j == undertoasted.length - 1)
                    return 1;
                else
                    return 2;
            }
        return 0;
    }
}

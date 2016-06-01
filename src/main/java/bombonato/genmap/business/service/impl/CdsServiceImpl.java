package bombonato.genmap.business.service.impl;

import bombonato.genmap.business.service.CdsService;

import java.util.List;

public class CdsServiceImpl implements CdsService {

    @Override
    public long calcCdsSize(List<Integer[]> cdsList) {

        long result = 0L;

        int index = 0;
        System.out.println("\n");
        System.out.println("--- CDS areas ---");
        for (Integer[] cds : cdsList) {
            System.out.println(++index + ":\t" + cds[0] + ".." + cds[1] + " = " + (cds[1] - cds[0]));

            result += cds[1] - cds[0];
        }

        System.out.println("\n");

        System.out.println("Total Size = " + result);

        return result;
    }
}

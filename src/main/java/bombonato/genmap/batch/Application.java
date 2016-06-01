package bombonato.genmap.batch;

import bombonato.genmap.business.service.CdsService;
import bombonato.genmap.business.service.MapJoinService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static final String PARAM_MAP_JOIN ="mapgbjoin=";
		public static final String PARAM_CDS_SIZE ="cds-size=";

	public static final String PARAM_HELP="--help";

	public static void main(String[] args)
			throws BeansException,
			JobExecutionAlreadyRunningException,
			JobRestartException,
			JobInstanceAlreadyCompleteException,
			JobParametersInvalidException,
			InterruptedException,
			IOException {

		System.out.println("##### GenMap #####");

		Log log = LogFactory.getLog(Application.class);

		if (args != null && args.length > 0) {

			Date start = new Date();
			Date end = null;

			boolean needHelp = false;

			List<Integer[]> exons = null;
			List<Integer[]> cdsList = null;

			// Prepare application
			for (int i = 0; i < args.length; i++) {
				if (args[i].startsWith(PARAM_HELP)) {
					needHelp = true;

				} else if (args[i].startsWith(PARAM_MAP_JOIN)) {
					String value[] = args[i].split("=");

					String joinStr = value[1];

					joinStr = joinStr.replace("join(", "");
					joinStr = joinStr.replace(")", "");
					joinStr = joinStr.replace(" ", "");

					String[] joinStrValues = joinStr.split(",");

					exons = new ArrayList<Integer[]>();

					for(String join : joinStrValues ) {
						String[] valuesStr = join.replace("..", "-").split("-");

						if (valuesStr != null && valuesStr.length == 2) {
							Integer[] values = new Integer[2];
							values[0] = new Integer(valuesStr[0]);
							values[1] = new Integer(valuesStr[1]);

							exons.add(values);
						}
					}
				} else if (args[i].startsWith(PARAM_CDS_SIZE)) {
					String value[] = args[i].split("=");

					String joinStr = value[1];

					joinStr = joinStr.replace("join(", "");
					joinStr = joinStr.replace(")", "");
					joinStr = joinStr.replace(" ", "");

					String[] joinStrValues = joinStr.split(",");

					cdsList = new ArrayList<Integer[]>();

					for(String join : joinStrValues ) {
						String[] valuesStr = join.replace("..", "-").split("-");

						if (valuesStr != null && valuesStr.length == 2) {
							Integer[] values = new Integer[2];
							values[0] = new Integer(valuesStr[0]);
							values[1] = new Integer(valuesStr[1]);

							cdsList.add(values);
						}
					}
				}
			}

			if (needHelp) {
				printHelp();
			} else {
				// Start Application
				SpringApplication app = new SpringApplication(Application.class);
				app.setWebEnvironment(false);
				app.setShowBanner(false);
				app.setLogStartupInfo(false);

				ConfigurableApplicationContext ctx = app.run(args);


				if (exons != null) {
					MapJoinService mapJoinService = ctx.getBean(MapJoinService.class);

					mapJoinService.mapJoin(
							exons
					);
				}

				if (cdsList != null) {
					CdsService cdsService = ctx.getBean(CdsService.class);

					cdsService.calcCdsSize(
							cdsList
					);
				}

				end = new Date();

				System.out.println("\n");
				System.out.println("Process started: " + start);
				System.out.println("Process finished: " + end);

			}

		} else {
			printHelp();
		}

		System.exit(0);
    }

	private static void printHelp() {
		System.out.println("Usage:");
		System.out.println("  $ java -jar genmap-1.0.0 mapgbjoin=\"join(1..123,800..1000)\"");

		System.out.println("Options:");
		System.out.println("  mapgbjoin     Will get the join (described in GenBank file) and map exons and introns");
		System.out.println("                spaces in this string is not allowed");
		System.out.println("");
		System.out.println("  cds-size      Calculate the CDS size. Works with or without join.");
		System.out.println("                spaces in this string is not allowed");
		System.out.println("");
		System.out.println("  --help        Show this help");

	}

}
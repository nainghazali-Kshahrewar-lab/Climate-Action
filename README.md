# Climate Action

BIT1123 Object Oriented Programming — Final Project (40%)
**SDG 13: Climate Action**

 ## Group Members
| Name | Student ID |Class code | Program |
|------|-----------|------------|---------|
|NURAIN BADRISYIA BINTI MOHD GHAZALI |202307010013 |202605F0780 |BCSSE |

## Project Structure

```
climate-action-manager/
├── src/
│   └── climateaction/
│       ├── Main.java                    # Main application / GUI startup
│       ├── DataStore.java               # Shared ArrayLists / application data
│       ├── User.java                    # User information
│       ├── Activity.java                # Abstract parent class
│       ├── TransportActivity.java       # Transport emissions
│       ├── EnergyActivity.java          # Energy emissions
│       ├── WasteActivity.java           # Waste emissions
│       ├── GreenAction.java             # Climate-friendly actions
│       ├── CarbonCalculator.java        # CO2 calculation logic
│       ├── RecommendationService.java   # Climate recommendations
│       ├── ReportService.java            # Reports/statistics
│       └── FileManager.java             # File saving/loading
├── data/
│   ├── activities.txt
│   ├── green_actions.txt
│   └── users.txt
│
├── README.md
```

## Testing
Testing is performed to ensure that the system correctly
calculates emissions, validates input, stores records,
loads saved data, and generates appropriate recommendations.

## Limitations
The carbon emission values used by the application are
estimated values for educational and simulation purposes.
The system does not represent a certified carbon accounting
system and does not provide official environmental measurements.

## SDG 13 Justification
This project supports **SDG 13: Climate Action** by helping users
understand and reduce the environmental impact of their daily
activities.

Activities such as transportation, electricity consumption, and
waste generation can contribute to carbon emissions. The Climate
Action Manager allows users to record these activities, calculate
their estimated CO₂ emissions, track their carbon footprint, and
record environmentally friendly actions.

The system also provides climate recommendations based on the
user's emission levels. Therefore, the application promotes
environmental awareness and encourages users to take practical
actions to reduce their carbon footprint.

The project aligns with SDG 13 by using technology to support
climate awareness, monitoring, and individual action.

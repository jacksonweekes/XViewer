import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        let userDefaults = UserDefaults(suiteName: "XViewerPreferences")!
        KoinIOSKt.doInitKoinIos(userDefaults: userDefaults)
    }
    
	var body: some Scene {
		WindowGroup {
			UpcomingLaunchesScreen()
		}
	}
}

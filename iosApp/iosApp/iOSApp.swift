import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KoinIOSKt.doInitKoinIos()
    }
    
	var body: some Scene {
		WindowGroup {
			UpcomingLaunchesScreen()
		}
	}
}

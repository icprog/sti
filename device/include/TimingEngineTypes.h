#ifndef STI_TIMINGENGINE_TIMINGENGINETYPES_H
#define STI_TIMINGENGINE_TIMINGENGINETYPES_H

#include <boost/shared_ptr.hpp>

#include <vector>
#include <map>

#include "DeviceTypes.h"
#include "SynchronizedMap.h"

#include "EventTime.h"

namespace STI
{
	//namespace Utils
	//{
	//	class MixedValue;
	//	typedef std::vector< boost::shared_ptr<STI::Utils::MixedValue> > MixedValueVector;
	//}
	
	namespace TimingEngine
	{

		class PlayOptions;
		typedef boost::shared_ptr<PlayOptions> PlayOptions_ptr;

		class EngineTimestamp;
		class EngineInstance;
		class EngineID;
		typedef std::set<const STI::TimingEngine::EngineID> EngineIDSet;
		
		class DocumentationOptions;
		class ParsingResultsTarget;
		class ParsingResultsHandler;
		typedef boost::shared_ptr<DocumentationOptions> DocumentationOptions_ptr;
		typedef boost::shared_ptr<ParsingResultsHandler> ParsingResultsHandler_ptr;
		typedef boost::shared_ptr<ParsingResultsTarget> ParsingResultsTarget_ptr;

//		class ServerParsingResultsTargetWrapper;
//		typedef boost::shared_ptr<ServerParsingResultsTargetWrapper> ServerParsingResultsTargetWrapper_ptr;

		class LoadAccessPolicy;
		class GlobalLoadAccessPolicy;
		typedef boost::shared_ptr<LoadAccessPolicy> LoadAccessPolicy_ptr;

		class Trigger;
		typedef boost::shared_ptr<Trigger> Trigger_ptr;

		class EventEngine;
		typedef boost::shared_ptr<STI::TimingEngine::EventEngine> EventEngine_ptr;
		class DeviceEventEngine;
		typedef boost::shared_ptr<STI::TimingEngine::DeviceEventEngine> DeviceEventEngine_ptr;

		class EventEngineManager;
		typedef boost::shared_ptr<EventEngineManager> EventEngineManager_ptr;
		typedef std::vector<EventEngineManager_ptr> EventEngineManagerVector;
		typedef boost::shared_ptr<EventEngineManagerVector> EventEngineManagerVector_ptr;

		class LocalEventEngineManager;
		typedef boost::shared_ptr<LocalEventEngineManager> LocalEventEngineManager_ptr;

		class QueuedEventEngineManager;
		typedef boost::shared_ptr<QueuedEventEngineManager> QueuedEventEngineManager_ptr;
		

//		class EventTime;

		class Channel;

		class TextPosition;

		class PartnerEventTarget;
		typedef boost::shared_ptr<PartnerEventTarget> PartnerEventTarget_ptr;

		class TimingEvent;
		class TimingEventGroup;
		class LocalTimingEvent;

		class TimingMeasurement;
		class ScheduledMeasurement;
		class TimingMeasurementResult;
		class TimingMeasurementGroup;

		class SynchronousEvent;

		class MasterTrigger;
		typedef boost::shared_ptr<MasterTrigger> MasterTrigger_ptr;
		
	//	typedef const std::vector< boost::shared_ptr<TimingEvent> > ConstTimingEventVector;
		typedef boost::shared_ptr<TimingEvent> TimingEvent_ptr;
		typedef std::vector< TimingEvent_ptr > TimingEventVector;
		typedef boost::shared_ptr<TimingEventVector> TimingEventVector_ptr;
		typedef boost::shared_ptr<TimingEventGroup> TimingEventGroup_ptr;
		typedef std::map<EventTime, TimingEventGroup_ptr > TimingEventGroupMap;	//map time to TimingEventGroup

		typedef std::map<STI::Device::DeviceIDString, TimingEventVector > DeviceTimingEventsMap;	//map time to TimingEventVectors, sorted by device target

		typedef boost::shared_ptr<SynchronousEvent> SynchronousEvent_ptr;
		typedef std::vector< SynchronousEvent_ptr > SynchronousEventVector;

		class PsuedoSynchronousEvent;
		typedef boost::shared_ptr<PsuedoSynchronousEvent> PsuedoSynchronousEvent_ptr;

		typedef boost::shared_ptr<TimingMeasurement> TimingMeasurement_ptr;
		typedef std::vector< TimingMeasurement_ptr > TimingMeasurementVector;
		
		
//		typedef std::map<EngineTimestamp, TimingMeasurementVector> TimingMeasurementMap;
		typedef boost::shared_ptr<TimingMeasurementGroup> TimingMeasurementGroup_ptr;
		typedef std::map<EngineTimestamp, TimingMeasurementGroup_ptr> TimingMeasurementGroupMap;
//		typedef STI::Utils::SynchronizedMap<EngineTimestamp, TimingMeasurementGroup_ptr> TimingMeasurementGroupMap;

		typedef boost::shared_ptr<TimingMeasurementResult> TimingMeasurementResult_ptr;
		typedef std::vector< TimingMeasurementResult_ptr > TimingMeasurementResultVector;

		typedef boost::shared_ptr<ScheduledMeasurement> ScheduledMeasurement_ptr;
		typedef std::vector< ScheduledMeasurement_ptr > ScheduledMeasurementVector;

		typedef boost::shared_ptr<Channel> Channel_ptr;
		typedef std::map<unsigned short, Channel_ptr> ChannelMap;	//map channel number to Channel

		class MeasurementResultsTarget;
		class MeasurementResultsHandler;
		typedef boost::shared_ptr<MeasurementResultsHandler> MeasurementResultsHandler_ptr;

	}
}



#endif
